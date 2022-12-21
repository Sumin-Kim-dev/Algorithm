import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2458 {
    int n;
    List<Integer>[] before;
    List<Integer>[] after;

    public static void main(String[] args) throws IOException {
        new BOJ2458().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        before = new List[n];
        after = new List[n];
        for (int i = 0; i < n; i++) {
            before[i] = new ArrayList<>();
            after[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            before[b].add(a);
            after[a].add(b);
        }
        System.out.println(count());
    }

    long count() {
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[i] = dfs(i, before) + dfs(i, after);
        }
        return Arrays.stream(count).filter(i -> i == n - 1).count();
    }

    int dfs(int i, List<Integer>[] lists) {
        boolean[] isVisited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        isVisited[i] = true;
        int count = 0;
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            for (int next : lists[curr]) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                stack.push(next);
                count++;
            }
        }
        return count;
    }
}
