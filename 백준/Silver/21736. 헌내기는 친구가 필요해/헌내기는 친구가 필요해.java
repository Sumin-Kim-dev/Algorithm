import java.io.*;
import java.util.*;

public class Main {
    
    static int n;
    static int m;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'I') {
                    queue.offer(new int[]{i, j});
                    map[i][j] = 'X';
                }
            }
        }
        int num = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc)) continue;
                if (map[nr][nc] == 'X') continue;
                if (map[nr][nc] == 'P') {
                    num++;
                }
                map[nr][nc] = 'X';
                queue.offer(new int[]{nr, nc});
            }
        }
        System.out.println(num > 0 ? num : "TT");
    }
    
    private static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}