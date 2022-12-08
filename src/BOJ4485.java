import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485 {
    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int MAX = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        new BOJ4485().io(br);
    }

    void io(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int t = 1; ; t++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            int[][] rupee = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    rupee[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(String.format("Problem %d: %d", t, solution(n, rupee))).append('\n');
        }
        System.out.println(sb);
    }

    int solution(int n, int[][] rupee) {
        int[][] min = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(min[i], MAX);
        }
        min[0][0] = rupee[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(new int[]{0, 0, min[0][0]});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[2] > min[curr[0]][curr[1]]) continue;
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1], curr[2]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                next[2] += rupee[next[0]][next[1]];
                if (next[2] < min[next[0]][next[1]]) {
                    min[next[0]][next[1]] = next[2];
                    pq.offer(next);
                }
            }
        }
        return min[n - 1][n - 1];
    }
}
