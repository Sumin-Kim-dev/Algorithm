import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new long[3][n][n];
		dp[0][0][1] = 1;
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if (i == 0 && j == 1) continue;
					if (map[i][j] == 1) continue;
					dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
					if (i > 0) dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
					if (i > 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
						dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
					}
				}
			}
		}
		System.out.println(dp[0][n - 1][n - 1] + dp[1][n - 1][n - 1] + dp[2][n - 1][n - 1]);
	}

}