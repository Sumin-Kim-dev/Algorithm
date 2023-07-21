import java.io.*;
import java.util.*;
 
public class Solution {
     
    static int n;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
     
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());
            sb.append(time(startR, startC, endR, endC)).append("\n");
        }
        System.out.println(sb);
    }
 
    private static int time(int startR, int startC, int endR, int endC) {
        int[][] time = new int[n][n];
        int r = startR;
        int c = startC;
        for (int[] row : time) {
            Arrays.fill(row, -1);
        }
         
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(new int[] {r, c, 0});
        time[r][c] = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nextR = curr[0] + dr[i];
                int nextC = curr[1] + dc[i];
                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) continue;
                if (map[nextR][nextC] == 1) continue;
                int nextT = time[curr[0]][curr[1]] + 1;
                if (map[nextR][nextC] == 2) {
                    nextT = 3 * ((nextT - 1) / 3 + 1);
                }
                if (time[nextR][nextC] == -1 || time[nextR][nextC] > nextT) {
                    time[nextR][nextC] = nextT;
                    pq.offer(new int[] {nextR, nextC, nextT});
                }
            }
        }
         
        return time[endR][endC];
    }
}