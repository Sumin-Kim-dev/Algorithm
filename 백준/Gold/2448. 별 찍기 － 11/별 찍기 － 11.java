import java.util.Scanner;

public class Main {

	static int[][] triangle;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		triangle = new int[n][2 * n - 1];
		draw(0, 0, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (triangle[i][j] == 1) sb.append('*');
				else sb.append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void draw(int r, int c, int size) {
		if (size == 3) {
			triangle[r][c + 2] = 1;
			triangle[r + 1][c + 1] = triangle[r + 1][c + 3] = 1;
			for (int i = 0; i < 5; i++) {
				triangle[r + 2][c + i] = 1;
			}
			return;
		}
		draw(r, c + size / 2, size / 2);
		draw(r + size / 2, c, size / 2);
		draw(r + size / 2, c + size, size / 2);
	}

}