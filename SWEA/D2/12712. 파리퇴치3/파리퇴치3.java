import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= t; tc++) {
            sb.append('#').append(tc).append(' ');
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(max(map, n, m)).append('\n');
        }
        System.out.println(sb);
    }
    
    private static int max(int[][] map, int n, int m) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int plus = map[i][j];
                int cross = map[i][j];
                for (int k = 1; k < m; k++) {
                    if (i - k >= 0) {
                        plus += map[i - k][j];
                        if (j - k >= 0) cross += map[i - k][j - k];
                    }
                    if (i + k < n) {
                        plus += map[i + k][j];
                        if (j + k < n) cross += map[i + k][j + k];
                    }
                    if (j - k >= 0) {
                        plus += map[i][j - k];
                        if (i + k < n) cross += map[i + k][j - k];
                    }
                    if (j + k < n) {
                        plus += map[i][j + k];
                        if (i - k > 0) cross += map[i - k][j + k];
                    }
                }
                if (plus > max) max = plus;
                if (cross > max) max = cross;
            }
        }
        return max;
    }
}