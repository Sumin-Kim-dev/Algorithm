import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		setApart();
		while (t-- > 0) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[k][n]).append('\n');
		}
		System.out.println(sb);
	}

	private static void setApart() {
		dp = new int[15][15];
		for (int j = 1; j <= 14; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= 14; i++) {
			for (int j = 1; j <= 14; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
			}
		}
	}

}