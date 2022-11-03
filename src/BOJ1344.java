import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1344 {
    static final int N = 18;
    static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17};
    int a, b;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        double probA = prob(a);
        double probB = prob(b);
        System.out.println(probA + probB - probA * probB);
    }

    double prob(int p) {
        double prob = 0;
        for (int prime : PRIMES) {
            prob += comb(N, prime) * Math.pow(p / 100.0, prime) * Math.pow(1 - p / 100.0, N - prime);
        }
        return prob;
    }

    int comb(int n, int k) {
        int[][] comb = new int[n + 1][n + 1];
        comb[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= n; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
        return comb[n][k];
    }

    public static void main(String[] args) throws IOException {
        new BOJ1344().io();
    }
}
