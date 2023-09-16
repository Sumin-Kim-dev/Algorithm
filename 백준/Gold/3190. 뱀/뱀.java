import java.io.*;
import java.util.*;

public class Main {
    
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int k = Integer.parseInt(br.readLine());
        while(k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = -1;
        }
        int l = Integer.parseInt(br.readLine());
        int[][] cmd = new int[l][2];
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            cmd[i][0] = t;
            if (c == 'L') cmd[i][1] = -1;
            else cmd[i][1] = 1;
        }
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offerFirst(new int[] {0, 0});
        map[0][0] = 1;
        int d = 1;
        int r = 0;
        int t = 1;
        while (true) {
            int[] head = snake.peekFirst();
            int hr = head[0] + dr[d];
            int hc = head[1] + dc[d];
            if (hr < 0 || hr >= n || hc < 0 || hc >= n) break;
            if (map[hr][hc] == 1) break;
            if (map[hr][hc] == 0) {
                int[] tail = snake.pollLast();
                map[tail[0]][tail[1]] = 0;
            }
            snake.offerFirst(new int[] {hr, hc});
            map[hr][hc] = 1;
            if (r < l && cmd[r][0] == t) {
                d = (d + cmd[r][1] + 4) % 4;
                r++;
            }
            t++;
        }
        System.out.println(t);
    }
}