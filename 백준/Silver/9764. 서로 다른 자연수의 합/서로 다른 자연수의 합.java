import java.util.Scanner;

public class Main {
	
	public static final int MOD = 100999;
	
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			System.out.println(solution(n));
		}
	}

	private static int solution(int n) {
		dp = new int[n + 1][n + 1];
		for (int j = 0; j <= n; j++) {
			dp[0][j] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i][j - 1];
				if (i >= j) dp[i][j] = (dp[i][j] + dp[i - j][j - 1]) % MOD;
			}
		}
		return dp[n][n];
	}
}