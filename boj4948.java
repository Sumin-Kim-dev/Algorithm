package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj4948 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			System.out.println(Bertrand(n));
		}
	}

	static int Bertrand(int n) {
		int count = 0;
		boolean isPrime[] = new boolean[2 * n];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		for (int i = 2; i * i <= 2 * n; i++) {
			if (!isPrime[i - 1])
				continue;
			for (int j = 2 * i; j <= 2 * n; j += i) {
				isPrime[j - 1] = false;
			}
		}
		for (int i = n; i < 2 * n; i++) {
			if (isPrime[i])
				count++;
		}
		return count;
	}
}