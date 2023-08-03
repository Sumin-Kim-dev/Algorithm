import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		prime(0, 0);
		System.out.println(sb);
	}

	private static void prime(int cnt, int curr) {
		if (cnt == n) {
			sb.append(curr).append('\n');
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (cnt == 0 && i == 0) continue;
			if (isPrime(curr * 10 + i)) {
				prime(cnt + 1, curr * 10 + i);
			}
		}
	}
	
	private static boolean isPrime(int p) {
		if (p == 1) return false;
		for (int i = 2; i * i <= p; i++) {
			if (p % i == 0) return false;
		}
		return true;
	}
}