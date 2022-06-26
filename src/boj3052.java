

import java.util.Scanner;

public class boj3052 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a[] = new int[10];
		int count = 0;
		boolean equal;
		for (int i = 0; i < 10; i++) {
			equal = false;
			a[i] = (in.nextInt()) % 42;
			for (int j = 0; j < i; j++) {
				if (a[i] == a[j]) {
					equal = true;
					break;
				}
			}
			if (!equal)
				count++;
		}
		System.out.println(count);
		in.close();
	}

}
