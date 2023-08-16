import java.util.Scanner;

public class Main {
	
	static int max;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		max = (1 << n) - 1;
		backtracking(0, 0, 0);
		System.out.println(count);
	}

	private static void backtracking(int col, int dia, int adia) {
		if (col == max) {
			count++;
			return;
		}
		int flag = max & ~(col | dia | adia);
		while (flag > 0) {
			int curr = flag & (-flag);
			flag -= curr;
			backtracking(col | curr, (dia | curr) >> 1, (adia | curr) << 1);
		}
	}

}