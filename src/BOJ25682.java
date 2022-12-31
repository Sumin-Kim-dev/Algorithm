import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25682 {
    public static void main(String[] args) throws IOException {
        new BOJ25682().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] chess = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = row.charAt(j);
                if (c == 'B') chess[i][j] = 1;
            }
        }
        System.out.println(solution(n, m, chess, k));
    }

    int solution(int n, int m, int[][] chess, int k) {
        int[][] subSum = subSum(n, m, chess);
        int min = k * k;
        for (int i = 0; i <= n - k; i++) {
            for (int j = 0; j <= m - k; j++) {
                int curr = subSum[i + k][j + k] - subSum[i][j + k] - subSum[i + k][j] + subSum[i][j];
                if (curr * 2 > k * k) curr = k * k - curr;
                if (min > curr) min = curr;
            }
        }
        return min;
    }

    int[][] subSum(int n, int m, int[][] chess) {
        int[][] subSum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                subSum[i + 1][j + 1] = subSum[i][j + 1];
                if (chess[i][j] == (i + j) % 2) subSum[i + 1][j + 1]++;
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                subSum[i + 1][j + 1] = subSum[i + 1][j] + subSum[i + 1][j + 1];
            }
        }
        return subSum;
    }
}
