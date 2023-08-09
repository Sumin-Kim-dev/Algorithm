import java.util.Scanner;

public class Main {
	
	public static final int MAX = 100999;
	
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
		return dp(n, 0);
	}

	private static int dp(int n, int k) {
		if (n == k) return dp[n][k] = 1;
		if (n < 3 && k > 0 || dp[n][k] > 0) return dp[n][k];
		if (n < 2 * k) return 0;
		dp[n][k] = 1;
		for (int i = k + 1; 2 * i < n - k; i++) {
			dp[n][k] = (dp[n][k] + dp(n - k, i)) % MAX;
		}
		return dp[n][k];
	}
}