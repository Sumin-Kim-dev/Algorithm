import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11779 {
    final int INF = 1_000_000_000;
    int n;
    Integer[][] bus;
    int start, end;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        bus = new Integer[n][n];
        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            if (bus[start][end] == null || cost < bus[start][end])
                bus[start][end] = cost;
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;
    }

    int[][] Dijkstra(int start) {
        int[][] minCost = new int[n][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++)
            minCost[i][0] = INF;
        minCost[start][0] = 0;
        minCost[start][1] = start;
        pq.add(new int[]{start, 0, start});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (minCost[curr[0]][0] < curr[1]) continue;
            for (int i = 0; i < n; i++) {
                if (bus[curr[0]][i] == null) continue;
                if (bus[curr[0]][i] + minCost[curr[0]][0] < minCost[i][0]) {
                    minCost[i][0] = bus[curr[0]][i] + minCost[curr[0]][0];
                    minCost[i][1] = curr[0];
                    pq.add(new int[]{i, minCost[i][0], minCost[i][1]});
                }
            }
        }
        return minCost;
    }

    void traceback(int start, int end) {
        int[][] minCost = Dijkstra(start);
        System.out.println(minCost[end][0]);
        Stack<Integer> cities = new Stack<>();
        int curr = end;
        cities.push(curr);
        while (curr != start) {
            curr = minCost[curr][1];
            cities.push(curr);
        }
        System.out.println(cities.size());
        StringBuilder sb = new StringBuilder();
        while (!cities.isEmpty()) {
            sb.append(cities.pop() + 1).append(' ');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        traceback(start, end);
    }

    public static void main(String[] args) throws IOException {
        new BOJ11779().solution();
    }
}
