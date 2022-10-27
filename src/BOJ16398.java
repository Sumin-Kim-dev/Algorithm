import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ16398 {
    int n;
    int[][] flow;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        flow = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                flow[i][j] = Integer.parseInt(str[j]);
            }
        }
        System.out.println(solution());
    }

    long solution() {
        long cost = 0;
        boolean[] isSelected = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{0, 0});
        int count = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (isSelected[curr[0]]) continue;
            isSelected[curr[0]] = true;
            cost += curr[1];
            if (++count == n) break;
            for (int j = 0; j < n; j++) {
                if (isSelected[j]) continue;
                if (dist[j] < flow[curr[0]][j]) continue;
                dist[j] = flow[curr[0]][j];
                pq.offer(new int[]{j, flow[curr[0]][j]});
            }
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        new BOJ16398().io();
    }
}
