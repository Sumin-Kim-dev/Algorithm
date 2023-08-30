import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < 3; k++) {
					if (j == k) continue;
					if (dp[i][j] > dp[i - 1][k]) dp[i][j] = dp[i - 1][k];
				}
				dp[i][j] += map[i - 1][j];
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (min > dp[n][i]) min = dp[n][i];
		}
		System.out.println(min);
	}

}