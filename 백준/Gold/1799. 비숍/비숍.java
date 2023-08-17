import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] odd = new int[n][n];
		int[][] even = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int r = (i + j) / 2;
				int c = (i - j + n - 1) / 2;
				if ((i + j) % 2 == 0) even[r][c] = Integer.parseInt(st.nextToken());
				else odd[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(max(even) + max(odd));
	}

	private static int max(int[][] arr) {
		map = arr;
		max = 0;
		backtracking(0, 0, 0);
		return max;
	}

	private static void backtracking(int cnt, int curr, int flag) {
		if (cnt == n) {
			if (curr > max) max = curr;
			return;
		}
		backtracking(cnt + 1, curr, flag);
		for (int i = 0; i < n; i++) {
			if (map[cnt][i] == 0) continue;
			if ((flag & 1 << i) != 0) continue;
			backtracking(cnt + 1, curr + 1, flag | 1 << i);
		}
	}

}