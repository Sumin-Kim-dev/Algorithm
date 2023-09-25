import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static List<Integer> virus = new ArrayList<>();
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		List<Integer> empty = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) empty.add(i * m + j);
				else if (map[i][j] == 2) virus.add(i * m + j);
			}
		}
		int size = empty.size();
		int min = n * m;
		for (int i = 0; i < size; i++) {
			int ci = empty.get(i);
			map[ci / m][ci % m] = 1;
			for (int j = i + 1; j < size; j++) {
				int cj = empty.get(j);
				map[cj / m][cj % m] = 1;
				for (int k = j + 1; k < size; k++) {
					int ck = empty.get(k);
					map[ck / m][ck % m] = 1;
					min = Math.min(min, danger(map));
					map[ck / m][ck % m] = 0;
				}
				map[cj / m][cj % m] = 0;
			}
			map[ci / m][ci % m] = 0;
		}
		System.out.println(size - min - 3);
	}

	private static int danger(int[][] map) {
		int danger = 0;
		boolean[][] visited = new boolean[n][m];
		Queue<Integer> queue = new ArrayDeque<>(virus);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int r = curr / m;
			int c = curr % m;
			if (!visited[r][c]) {
				visited[r][c] = true;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					danger++;
					queue.offer(nr * m + nc);
				}
			}
		}
		return danger;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}