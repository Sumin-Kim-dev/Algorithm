package boj;

import java.util.Scanner;

public class BOJ1193 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		int numPlusDenom = 1;
		int count = 0;
		while (count < X) {
			numPlusDenom++;
			count += (numPlusDenom - 1);
		}
		int num, denom;
		if (numPlusDenom % 2 == 1) {
			num = numPlusDenom - (count - X + 1);
			denom = count - X + 1;
		} else {
			num = count - X + 1;
			denom = numPlusDenom - (count - X + 1);
		}
		System.out.printf("%d/%d", num, denom);
	}
}
