import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057 {
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        new BOJ11057().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    int solution(int n) {
        // n+9C9
        int[][] comb = new int[n + 10][10];
        comb[0][0] = 1;
        for (int i = 1; i <= n + 9; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= 9; j++) {
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
            }
        }
        return comb[n + 9][9];
    }
}
