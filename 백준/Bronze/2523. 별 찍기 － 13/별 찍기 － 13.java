import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() - 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 2 * n; i++) {
			for (int j = 0; j < n + 1 - Math.abs(n - i); j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		sc.close();
	}
}
