package boj;

import java.util.Scanner;

public class BOJ1065 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (hansu(i))
				count++;
		}
		System.out.println(count);
	}

	static boolean hansu(int X) {
		if (X < 100)
			return true;
		else
			return (X / 100 + X % 10) == 2 * ((X / 10) % 10);
	}
}
