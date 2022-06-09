package boj;

import java.util.Scanner;

public class boj2562 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a[] = new int[9];
		int max = 0;
		int max_idx = 1;
		for (int i = 1; i <= 9; i++) {
			a[i - 1] = in.nextInt();
			if (a[i - 1] >= max) {
				max = a[i - 1];
				max_idx = i;
			}
		}
		System.out.println(max);
		System.out.println(max_idx);
	}

}
