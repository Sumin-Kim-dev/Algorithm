import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(new BOJ11058().solution(n));
    }

    long solution(int n) {
        long[] max = new long[n + 1];
        max[0] = 0;
        for (int i = 1; i <= n; i++) {
            max[i] = max[i - 1] + 1;
            for (int j = 1; j <= i - 3; j++) {
                max[i] = Math.max(max[i], max[j] * (i - 1 - j));
            }
        }
        return max[n];
    }
}
