package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj9020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n;
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			Goldbach(n);
		}
	}

	static boolean prime(int p) {
		if (p == 1)
			return false;
		for (int i = 2; i * i <= p; i++) {
			if (p % i == 0 && p > i)
				return false;
		}
		return true;
	}

	static void Goldbach(int n) {
		for (int i = 0; i < n / 2; i++) {
			if (prime(n / 2 - i) && prime(n / 2 + i)) {
				System.out.printf("%d %d\n", n / 2 - i, n / 2 + i);
				break;
			}
		}
	}
}
