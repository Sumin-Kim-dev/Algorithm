import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] stuffs = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			stuffs[i][0] = v;
			stuffs[i][1] = c;
		}
		int[] dp = new int[m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = m; j >= 0; j--) {
				if (j >= stuffs[i][0]) dp[j] = Math.max(dp[j], dp[j - stuffs[i][0]] + stuffs[i][1]);
			}
		}
		System.out.println(dp[m]);
	}

}