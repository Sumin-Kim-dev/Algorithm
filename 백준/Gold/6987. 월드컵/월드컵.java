import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n = 6;
	static int[][] result = new int[n][3];
	static boolean isAble;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			isAble = false;
			int sum = 0;
			for (int j = 0; j < n * 3; j++) {
				result[j / 3][j % 3] = Integer.parseInt(st.nextToken());
				sum += result[j / 3][j % 3];
			}
			if (sum == n * (n - 1)) backtracking(0, 1);
			System.out.print((isAble ? 1 : 0) + " ");
		}
	}

	private static void backtracking(int g1, int g2) {
		if (isAble) return;
		if (g1 == n - 1) {
			isAble = true;
			return;
		}
		int ng1 = g1;
		int ng2 = g2 + 1;
		if (ng2 == n) {
			ng1 = g1 + 1;
			ng2 = ng1 + 1;
		}
		for (int i = 0; i <= 2; i++) {
			if (result[g1][i] > 0 && result[g2][2 - i] > 0) {
				result[g1][i]--;
				result[g2][2 - i]--;
				backtracking(ng1, ng2);
				result[g1][i]++;
				result[g2][2 - i]++;
			}
		}
	}
}