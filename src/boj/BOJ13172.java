package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ13172 {

	static final int prime = 1000000007;
	static final String binaryPrime = Integer.toBinaryString(prime - 2);

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int m = Integer.parseInt(br.readLine());
		long sum = 0;
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n = Integer.parseInt(st.nextToken());
			long s = Integer.parseInt(st.nextToken());
			sum = (sum + s * inverse(n)) % prime;
		}
		bw.write(sum + "");
		bw.close();
	}

	static long inverse(long n) {
		long inverse = n;
		for (int i = 1; i < binaryPrime.length(); i++) {
			inverse = (inverse * inverse) % prime;
			if (binaryPrime.charAt(i) == '1')
				inverse = (inverse * n) % prime;
		}
		return inverse;
	}
}
