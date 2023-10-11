import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int prime = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long answer = (factorial(n) * inv((factorial(r) * factorial(n - r)) % prime)) % prime;
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	private static long factorial(int n) {
		long fact = 1;
		for (int i = 2; i <= n; i++) {
			fact *= i;
			fact %= prime;
		}
		return fact;
	}
	
	private static long inv(long n) {
		int k = prime - 2;
		long answer = 1;
		while (k > 0) {
			if ((k & 1) != 0) answer = (answer * n) % prime;
			n = (n * n) % prime;
			k >>= 1;
		}
		return answer;
	}

}