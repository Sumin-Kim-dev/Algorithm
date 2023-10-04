import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<int[]> stuffs = new ArrayList<>();
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int ck = 1;
			while (k > 0) {
				if (k < ck) ck = k;
				stuffs.add(new int[] {v * ck, c * ck});
				k -= ck;
				ck <<= 1;
			}
		}
		n = stuffs.size();
		int[] dp = new int[m + 1];
		for (int i = 0; i < n; i++) {
			int cv = stuffs.get(i)[0];
			int cc = stuffs.get(i)[1];
			for (int j = m; j >= cv; j--) {
				dp[j] = Math.max(dp[j], dp[j - cv] + cc);
			}
		}
		System.out.println(dp[m]);
	}

}