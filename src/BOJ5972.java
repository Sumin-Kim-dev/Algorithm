import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5972 {
    int n, m;
    List<int[]>[] roads;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        roads = new List[n];
        for (int i = 0; i < n; i++) {
            roads[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            roads[start].add(new int[]{end, cost});
            roads[end].add(new int[]{start, cost});
        }
        System.out.println(dijkstra(0, n - 1));
    }

    int dijkstra(int start, int end) {
        final int INF = 100_000_000;
        int[] minCost = new int[n];
        Arrays.fill(minCost, INF);
        minCost[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, minCost[start]});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (minCost[curr[0]] < curr[1]) continue;
            for (int[] road : roads[curr[0]]) {
                if (minCost[curr[0]] + road[1] < minCost[road[0]]) {
                    minCost[road[0]] = minCost[curr[0]] + road[1];
                    pq.offer(new int[]{road[0], minCost[road[0]]});
                }
            }
        }
        return minCost[end];
    }

    public static void main(String[] args) throws IOException {
        new BOJ5972().io();
    }
}
