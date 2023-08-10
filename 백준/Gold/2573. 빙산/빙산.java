import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	static int ices;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		ices = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) ices++;
			}
		}
		int t = 0;
		while (melt()) {
			t++;
		}
		System.out.println(ices > 0 ? t : 0);
	}

	private static boolean melt() {
		if (ices == 0) return false;
		int[][] after = new int[n][m];
		for (int i = 0; i < n; i++) {
			after[i] = Arrays.copyOf(map[i], m);
		}
		if (count() > 1) return false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) continue;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (!isIn(nr, nc)) continue;
					if (after[nr][nc] > 0) {
						after[nr][nc]--;
						if (after[nr][nc] == 0) ices--;
					}
				}
			}
		}
		map = after;
		return true;
	}

	private static int count() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] <= 0) continue;
				count++;
				dfs(i, j);
			}
		}
		return count;
	}

	private static void dfs(int i, int j) {
		map[i][j] = -1;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (!isIn(nr, nc)) continue;
			if (map[nr][nc] <= 0) continue;
			dfs(nr, nc);
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}