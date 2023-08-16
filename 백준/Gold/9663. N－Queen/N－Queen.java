import java.util.Scanner;

public class Main {
	
	static int n;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		backtracking(0, 0, 0, 0);
		System.out.println(count);
	}

	private static void backtracking(int r, int col, int dia, int adia) {
		if (r == n) {
			count++;
			return;
		}
		for (int c = 0; c < n; c++) {
			if ((col & 1 << c) != 0) continue;
			if ((dia & 1 << (r + c)) != 0) continue;
			if ((adia & 1 << (r - c + n)) != 0) continue;
			backtracking(r + 1, col | 1 << c, dia | 1 << (r + c), adia | 1 << (r - c + n));
		}
	}

}