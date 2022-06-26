import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11401 {

	final static int p = 1000000007;
	final static String binary = Integer.toBinaryString(p - 2);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long ans = (Factorial(n) * inv(Factorial(n - k) * Factorial(k))) % p;
		bw.write(ans + "");
		bw.close();
	}

	static long inv(long k) {
		k %= p;
		long inv = 1;
		for (int i = 0; i < binary.length(); i++) {
			inv = (inv * inv) % p;
			if (binary.charAt(i) == '1')
				inv = (inv * k) % p;
		}
		return inv;
	}

	static long Factorial(int n) {
		long ans = 1;
		while (n > 1) {
			ans = (ans * n) % p;
			n--;
		}
		return ans;
	}
}
