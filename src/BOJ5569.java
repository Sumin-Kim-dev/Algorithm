import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5569 {
    final int MAX = 100000;
    int w, h;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken()) - 1;
        h = Integer.parseInt(st.nextToken()) - 1;
    }

    int[][][] north, east;

    void dp() {
        north[0][1][0] = 1;
        east[0][0][1] = 1;
        for (int k = 0; k < w + h; k++) {
            for (int i = 0; i <= k + 1; i++) {
                int j = k + 1 - i;
                if (i > 0 && i <= w && j > 0 && j <= h) {
                    north[k][i][j] = east[0][i - 1][j];
                    for (int l = 1; l <= k - 2; l++) {
                        north[k][i][j] += east[l][i - 1][j];
                        north[k][i][j] %= MAX;
                    }
                }
                if (i > 0 && i <= w && j > 0 && j <= h) {
                    east[k][i][j] = north[0][i][j - 1];
                    for (int l = 1; l <= k - 2; l++) {
                        east[k][i][j] += north[l][i][j - 1];
                        east[k][i][j] %= MAX;
                    }
                }
            }
            for (int i = 0; i <= w; i++) {
                for (int j = 0; j <= h; j++) {
                    if (i > 0 && north[k][i - 1][j] != 0) north[k][i][j] = north[k][i - 1][j];
                    if (j > 0 && east[k][i][j - 1] != 0) east[k][i][j] = east[k][i][j - 1];
                }
            }
        }
    }

    void solution() throws IOException {
        input();
        north = new int[w + h][w + 1][h + 1];
        east = new int[w + h][w + 1][h + 1];
        dp();
        int count = 0;
        for (int k = 0; k < w + h; k++) {
            count += north[k][w][h];
            count += east[k][w][h];
            count %= MAX;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ5569().solution();
    }
}
