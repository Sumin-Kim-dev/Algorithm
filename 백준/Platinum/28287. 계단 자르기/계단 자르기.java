import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int mod = Integer.parseInt(st.nextToken());
		
		long[] f = new long[n + 1];
		long[] g = new long[n + 1];
		long[] h = new long[n + 1];
		g[0] = 1;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int k = 0; k <= i - 1; k++) {
				g[i] = (g[i] + g[k] * g[i - k - 1]) % mod;
				long temp = (g[k] * g[i - k]) % mod;
				h[i] = (h[i] + g[k] * h[i - k - 1] + k * temp) % mod;
				f[i] = (f[i] + 2 * f[k] * g[i - k - 1]) % mod;
			}
			f[i] = (f[i] + h[i]) % mod;
			sb.append(f[i]).append(' ');
		}
		System.out.println(sb);
	}

}
