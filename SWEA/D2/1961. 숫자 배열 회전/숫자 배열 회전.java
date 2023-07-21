import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= t; k++) {
            sb.append('#').append(k).append('\n');
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(arr[n - 1 - j][i]);
                }
                sb.append(' ');
                for (int j = 0; j < n; j++) {
                    sb.append(arr[n - 1 - i][n - 1 - j]);
                }
                sb.append(' ');
                for (int j = 0; j < n; j++) {
                    sb.append(arr[j][n - 1 - i]);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}