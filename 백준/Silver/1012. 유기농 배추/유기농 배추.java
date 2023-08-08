import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			int k = Integer.parseInt(st.nextToken());
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			sb.append(count()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int count() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 1) continue;
				cnt++;
				bfs(i, j, cnt + 1);
			}
		}
		return cnt;
	}

	private static void bfs(int i, int j, int g) {
		Queue<int[]> queue = new ArrayDeque<>();
		map[i][j] = g;
		queue.offer(new int[] {i, j});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] != 1) continue;
				map[nr][nc] = g;
				queue.offer(new int[] {nr, nc});
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < m;
	}
}