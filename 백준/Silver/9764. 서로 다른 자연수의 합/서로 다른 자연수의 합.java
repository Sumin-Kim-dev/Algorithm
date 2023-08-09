import java.util.Scanner;

public class Main {
	
	public static final int MOD = 100999;
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			System.out.println(solution(n));
		}
	}

	private static int solution(int n) {
		dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = n; j >= i; j--) {
				dp[j] = (dp[j] + dp[j - i]) % MOD;
			}
		}
		return dp[n];
	}
}