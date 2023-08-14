import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int j = n / 5; j >= 0; j--) {
			if ((n - 5 * j) % 3 == 0) {
				System.out.println(j + (n - 5 * j) / 3);
				return;
			}
		}
		System.out.println(-1);
	}

}