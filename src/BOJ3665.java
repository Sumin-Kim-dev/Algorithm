import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3665 {
    int n;
    int[] inputs;
    ArrayList<Integer>[] edges;

    void input(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        inputs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            inputs[Integer.parseInt(st.nextToken())] = i - 1;
        }
        setEdges();
        updateEdges(br);
    }

    void setEdges() {
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (inputs[j] > inputs[i]) edges[i].add(j);
            }
        }
    }

    void updateEdges(BufferedReader br) throws IOException {
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (edges[b].contains(a)) {
                int temp = b;
                b = a;
                a = temp;
            }
            edges[a].remove((Integer) b);
            inputs[b]--;
            edges[b].add(a);
            inputs[a]++;
        }
    }

    String topologicalSort() {
        Queue<Integer> emptyInput = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inputs[i] == 0) emptyInput.offer(i);
        }
        int[] sortResult = new int[n];
        int currIndex = 0;
        while (!emptyInput.isEmpty()) {
            if (emptyInput.size() > 1) return "?";
            int curr = emptyInput.poll();
            sortResult[currIndex++] = curr;
            for (int next : edges[curr]) {
                inputs[next]--;
                if (inputs[next] == 0) emptyInput.offer(next);
            }
        }
        if (currIndex < n) return "IMPOSSIBLE";
        else return makeString(sortResult);
    }

    String makeString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j).append(' ');
        }
        return sb.toString();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            input(br);
            System.out.println(topologicalSort());
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ3665().solution();
    }
}
