import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1749 {
    static final int MIN = -400000000;
    int n, m;
    int[][] arr;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int maxSum() {
        int maxSum = MIN;
        int[][] columnCumulativeSum = new int[n + 1][m];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                columnCumulativeSum[i][j] = columnCumulativeSum[i - 1][j] + arr[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int[] columnSubSum = new int[m];
                for (int k = 0; k < m; k++)
                    columnSubSum[k] = columnCumulativeSum[j][k] - columnCumulativeSum[i][k];
                int maxSubSum = maxSubSum(columnSubSum);
                if (maxSum < maxSubSum)
                    maxSum = maxSubSum;
            }
        }
        return maxSum;
    }

    int maxSubSum(int[] arr) {
        int[] maxSubSum = new int[arr.length];
        maxSubSum[0] = arr[0];
        int max = maxSubSum[0];
        for (int i = 1; i < arr.length; i++) {
            maxSubSum[i] = arr[i];
            if (maxSubSum[i - 1] > 0) maxSubSum[i] += maxSubSum[i - 1];
            if (max < maxSubSum[i])
                max = maxSubSum[i];
        }
        return max;
    }

    void solution() throws IOException {
        input();
        System.out.println(maxSum());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1749().solution();
    }
}
