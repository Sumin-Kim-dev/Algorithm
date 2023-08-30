import java.util.Scanner;

public class Main {
	
	public static final int MOD = 1_000_000_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][][] dp = new int[n][1 << 10][10];
		for (int i = 1; i <= 9; i++) {
			dp[0][1 << i][i] = 1;
		}
		for (int k = 1; k < n; k++) {
			for (int i = 0; i < 1 << 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (j > 0) dp[k][i | (1 << j)][j] = (dp[k][i | (1 << j)][j] + dp[k - 1][i][j - 1]) % MOD;
					if (j < 9) dp[k][i | (1 << j)][j] = (dp[k][i | (1 << j)][j] + dp[k - 1][i][j + 1]) % MOD;
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer = (answer + dp[n - 1][(1 << 10) - 1][i]) % MOD;
		}
		System.out.println(answer);
	}

}