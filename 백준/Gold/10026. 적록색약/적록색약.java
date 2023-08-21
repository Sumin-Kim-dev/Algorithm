import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int n;
	static char[][] map;
	static boolean[][] visited1;
	static boolean[][] visited2;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited1 = new boolean[n][n];
		visited2 = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited1[i][j]) {
					bfs1(i, j);
					count1++;
				}
				if (!visited2[i][j]) {
					bfs2(i, j);
					count2++;
				}
			}
		}
		System.out.println(count1 + " " + count2);
	}

	private static void bfs1(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		visited1[i][j] = true;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited1[nr][nc]) continue;
				if (map[r][c] == map[nr][nc]) {
					visited1[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}
	
	private static void bfs2(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		visited2[i][j] = true;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited2[nr][nc]) continue;
				if (map[r][c] == map[nr][nc] || map[r][c] != 'B' && map[nr][nc] != 'B') {
					visited2[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < n;
	}

}