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
		return pow(n, prime - 2);
	}

	private static long pow(long n, int i) {
		if (i == 0) return 1;
		long half = pow(n, i / 2);
		long part = (half * half) % prime;
		if ((i & 1) == 0) return part;
		return (n * part) % prime;
	}

}