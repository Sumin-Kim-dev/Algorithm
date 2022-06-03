package boj;

import java.util.Arrays;

public class boj4673 {

	public static void main(String[] args) {
		boolean self[] = new boolean[10000];
		Arrays.fill(self, true);
		for (int i = 0; i < 10000; i++) {
			if (d(i + 1) <= 10000)
				self[d(i + 1) - 1] = false;
		}
		for (int i = 0; i < 10000; i++) {
			if (self[i])
				System.out.println(i + 1);
		}
	}

	static int d(int n) {
		int d_n = n;
		while (n != 0) {
			d_n += n % 10;
			n /= 10;
		}
		return d_n;
	}
}
