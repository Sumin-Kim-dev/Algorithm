

import java.util.Scanner;

public class boj2577 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int N = a * b * c;
		int count[] = new int[10];
		while (N != 0) {
			count[N % 10] += 1;
			N /= 10;
		}
		for (int i = 0; i < 10; i++)
			System.out.println(count[i]);
		in.close();
	}

}
