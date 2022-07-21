import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1757 {
    int n, m;
    int[] dist;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n];
        for (int i = 0; i < n; i++)
            dist[i] = Integer.parseInt(br.readLine());
    }

    int maxDist() {
        // maxDist[0][i][j] : 마지막 i분때 안 달리고 지침지수 j로 끝남
        // maxDist[1][i][j] : 마지막 i분때 달리고 지침지수 j로 끝남
        int[][][] maxDist = new int[2][n][m + 1];
        maxDist[1][0][1] = dist[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < m) maxDist[0][i][j] = Math.max(maxDist[0][i - 1][j + 1], maxDist[1][i - 1][j + 1]);
                if (j == 0 && maxDist[0][i][0] < Math.max(maxDist[0][i - 1][0], maxDist[1][i - 1][0]))
                    maxDist[0][i][0] = Math.max(maxDist[0][i - 1][0], maxDist[1][i - 1][0]);
                if (j == 1) maxDist[1][i][j] = maxDist[0][i - 1][0] + dist[i];
                if (j > 1) maxDist[1][i][j] = maxDist[1][i - 1][j - 1] + dist[i];
            }
        }
        return maxDist[0][n - 1][0];
    }

    void solution() throws IOException {
        input();
        System.out.println(maxDist());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1757().solution();
    }
}
