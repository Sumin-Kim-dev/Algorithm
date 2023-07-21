import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
 
public class Solution {
     
    static int n;
    static int[][] map;
    static int[][] deltas = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = row.charAt(j) - '0';
                }
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }
 
    private static int solution() {
        int[][] time = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(time[i], 10000000);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        queue.offer(new int[] {0, 0, 0});
        time[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currR = curr[0];
            int currC = curr[1];
            int currT = curr[2];
            for (int d = 0; d < 4; d++) {
                int nextR = currR + deltas[0][d];
                int nextC = currC + deltas[1][d];
                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) continue;
                if (currT + map[nextR][nextC] < time[nextR][nextC]) {
                    time[nextR][nextC] = currT + map[nextR][nextC];
                    if (nextR == n - 1 && nextC == n - 1) {
                        return time[nextR][nextC];
                    }
                    queue.offer(new int[] {nextR, nextC, time[nextR][nextC]});
                }
            }
        }
        return -1;
    }
     
}