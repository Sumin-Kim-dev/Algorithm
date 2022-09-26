import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1162 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String[] roads = new String[m];
        for (int i = 0; i < m; i++) {
            roads[i] = br.readLine();
        }
        System.out.println(solution(n, m, k, roads));
    }

    long solution(int n, int m, int k, String[] roads) {
        ArrayList<int[]>[] adj = setAdj(n, m, roads);
        return solution(n, k, adj);
    }

    ArrayList<int[]>[] setAdj(int n, int m, String[] roads) {
        ArrayList<int[]>[] adj = new ArrayList[n]; // 도착지, 시간 순서
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(roads[i]);
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adj[s].add(new int[]{e, w});
            adj[e].add(new int[]{s, w});
        }
        return adj;
    }

    long solution(int n, int k, ArrayList<int[]>[] adj) {
        final long INF = 1_000_000_000_000L;
        long[][] minDist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                minDist[i][j] = INF;
            }
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[2]));
        pq.offer(new long[]{0, 0, 0}); // 도착지, 포장한 도로 개수, 시간 순서
        minDist[0][0] = 0;
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int city = (int) curr[0];
            int count = (int) curr[1];
            if (minDist[city][count] < curr[2]) continue;
            for (int[] road : adj[city]) {
                if (minDist[city][count] + road[1] < minDist[road[0]][count]) {
                    minDist[road[0]][count] = minDist[city][count] + road[1];
                    pq.offer(new long[]{road[0], count, minDist[road[0]][count]});
                }
                if (count < k && minDist[city][count] < minDist[road[0]][count + 1]) {
                    minDist[road[0]][count + 1] = minDist[city][count];
                    pq.offer(new long[]{road[0], count + 1, minDist[road[0]][count + 1]});
                }
            }
        }
        return Arrays.stream(minDist[n - 1]).min().getAsLong();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1162().io();
    }
}
