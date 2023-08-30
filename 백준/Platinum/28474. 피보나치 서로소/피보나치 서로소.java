import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(n % 2 == 0 ? phi(n) + phi(n / 2) : phi(n)).append('\n');
		}
		System.out.println(sb);
	}

	private static int phi(int n) {
		if (n == 1) return 0;
		int answer = n;
		for (int p = 2; p * p <= n; p++) {
			if (n % p != 0) continue;
			while (n % p == 0) {
				n /= p;
			}
			answer = answer / p * (p - 1);
		}
		if (n > 1) answer = answer / n * (n - 1);
		return answer;
	}

}