import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    int n;
    int[] arr;
    int ans;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) - 1;
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.parseInt(st.nextToken());
        System.out.println(solution(n, arr, ans));
    }

    long solution(int n, int[] arr, int ans) {
        this.n = n;
        this.arr = arr;
        this.ans = ans;
        long[][] count = new long[n][21];
        count[0][arr[0]] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 20; j++) {
                if (j - arr[i] >= 0) count[i][j] += count[i - 1][j - arr[i]];
                if (j + arr[i] <= 20) count[i][j] += count[i - 1][j + arr[i]];
            }
        }
        return count[n - 1][ans];
    }

    public static void main(String[] args) throws IOException {
        new BOJ5557().io();
    }
}
