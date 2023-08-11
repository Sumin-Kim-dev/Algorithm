import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] list;
    static int[] visited;
    static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new List[n];
        visited = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            list[v1].add(v2);
            list[v2].add(v1);
        }
        for (int i = 0; i < n; i++) {
            if (!finish) dfs(i, i, 0);
        }

        Queue<Integer> cycle = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (visited[i] == 1) cycle.offer(i);
        }

        while (!cycle.isEmpty()) {
            int curr = cycle.poll();
            for (int next : list[curr]) {
                if (visited[next] > 0) continue;
                visited[next] = visited[curr] + 1;
                cycle.offer(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(visited[i] - 1).append(' ');
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int curr, int length) {
        visited[curr] = 1;
        for (int next : list[curr]) {
            if (start == next && length  >= 2) {
                finish = true;
                return;
            }
            if (visited[next] == 1) continue;
            dfs(start, next, length + 1);
            if (finish) return;
        }
        visited[curr] = 0;
    }
}