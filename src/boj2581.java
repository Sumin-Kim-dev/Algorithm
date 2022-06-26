

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2581 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		boolean[] isPrime = prime(b);
		boolean empty = true;
		int sum = 0;
		int min = 0;
		for (int i = a; i <= b; i++) {
			if (isPrime[i - 1]) {
				sum += i;
				if (empty) {
					min = i;
					empty = false;
				}
			}
		}
		if (empty)
			System.out.println(-1);
		else
			System.out.printf("%d\n%d", sum, min);
	}

	static boolean[] prime(int b) {
		boolean isPrime[] = new boolean[b];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		for (int i = 2; i * i <= b; i++) {
			int j = 2 * i;
			while (j <= b) {
				isPrime[j - 1] = false;
				j += i;
			}
		}
		return isPrime;
	}
}
