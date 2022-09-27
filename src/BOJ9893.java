import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9893 {
    final int INF = 100_000_000;
    int m, n;
    ArrayList<int[]>[] adj;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        adj = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            adj[i] = new ArrayList<>();
        }
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[s].add(new int[]{e, w});
        }
    }

    void solution() throws IOException {
        input();
        int[] paths = Dijkstra(0, 1);
        int i = 0;
        while (paths[i] == INF) {
            i++;
        }
        System.out.println(paths[i]);
    }

    int[] Dijkstra(int start, int end) {
        int[][] cost = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                cost[i][j] = INF;
            }
        }
        cost[start][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // 도착지 거리 cost 순
            if (cost[curr[0]][curr[1]] < curr[2]) continue;
            if (curr[1] == m - 1) continue;
            for (int[] next : adj[curr[0]]) {
                if (cost[curr[0]][curr[1]] + next[1] < cost[next[0]][curr[1] + 1]) {
                    cost[next[0]][curr[1] + 1] = cost[curr[0]][curr[1]] + next[1];
                    pq.offer(new int[]{next[0], curr[1] + 1, cost[next[0]][curr[1] + 1]});
                }
            }
        }
        return cost[end];
    }

    public static void main(String[] args) throws IOException {
        new BOJ9893().solution();
    }
}
