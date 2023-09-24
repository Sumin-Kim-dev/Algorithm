import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            adj[0][i] = adj[i][0] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                adj[i][j] = adj[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        int[] min = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{0, min[0]});
        int tot = 0;
        while (!pq.isEmpty()) {
            int[] c = pq.poll();
            int curr = c[0];
            if (visited[curr]) continue;
            tot += c[1];
            visited[curr] = true;
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && adj[curr][i] < min[i]) {
                    min[i] = adj[curr][i];
                    pq.offer(new int[]{i, min[i]});
                }
            }
        }
        System.out.println(tot);
    }
}