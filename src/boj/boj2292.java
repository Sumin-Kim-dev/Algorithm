package boj;

import java.util.Scanner;

public class boj2292 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int room = 1;
		int answer = 0;
		do {
			room += 6 * answer;
			answer++;
		} while (room < N);
		System.out.println(answer);
	}
}
