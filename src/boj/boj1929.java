package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1929 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		boolean[] isPrime = prime(b);
		for (int i = a; i <= b; i++) {
			if (isPrime[i - 1]) {
				System.out.println(i);
			}
		}
	}

	static boolean[] prime(int b) {
		boolean isPrime[] = new boolean[b];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		for (int i = 2; i * i <= b; i++) {
			if (!isPrime[i - 1])
				continue;
			for (int j = 2 * i; j <= b; j += i) {
				isPrime[j - 1] = false;
			}
		}
		return isPrime;
	}
}