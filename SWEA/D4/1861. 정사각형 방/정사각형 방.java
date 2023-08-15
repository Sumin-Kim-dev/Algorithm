import java.io.*;
import java.util.*;

public class Solution {

    static int n;
    static int[][] map;
    static int[][] remains;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int start = 1000001;
            int max = 0;
            remains = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (remains[i][j] > 0) continue;
                    dfs(i, j);
                    if (remains[i][j] >= max) {
                        if (remains[i][j] > max || start > map[i][j]) start = map[i][j];
                        max = remains[i][j];
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(start).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int r, int c) {
        if (remains[r][c] > 0) return remains[r][c];
        remains[r][c] = 1;
        int next = map[r][c] + 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isIn(nr, nc)) continue;
            if (map[nr][nc] == next) {
                remains[r][c] = Math.max(dfs(nr, nc) + 1, remains[r][c]);
            }
        }
        return remains[r][c];
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}