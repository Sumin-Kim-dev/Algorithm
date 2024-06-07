import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(map[i][j - 1] != map[i][j]) {
                    cnt++;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i - 1][j] != map[i][j]) {
                    cnt++;
                }
                if(i % 2 == 0 && j > 0 && map[i - 1][j - 1] != map[i][j]
                  || i % 2 == 1 && j < m - 1 && map[i - 1][j + 1] != map[i][j]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}