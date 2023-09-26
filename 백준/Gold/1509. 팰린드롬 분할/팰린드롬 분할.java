import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static char[] str;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		n = str.length;
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				if (str[j] == str[j + i] && (i < 2 || dp[i - 2][j + 1] == 1)) {
					dp[i][j] = 1;
				}
			}
		}
		System.out.println(dp(n - 1, 0));
	}

	private static int dp(int lengthMinus1, int start) {
		if (dp[lengthMinus1][start] > 0) return dp[lengthMinus1][start];
		dp[lengthMinus1][start] = lengthMinus1 + 1;
		for (int kMinus1 = lengthMinus1 - 1; kMinus1 >= 0; kMinus1--) {
			if (dp[kMinus1][start] != 1) continue;
			dp[lengthMinus1][start] = Math.min(dp[lengthMinus1][start], dp(lengthMinus1 - kMinus1 - 1, start + kMinus1 + 1) + 1);
		}
		return dp[lengthMinus1][start];
	}

}