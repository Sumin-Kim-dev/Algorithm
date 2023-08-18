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
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		if (n == 1 && m == 1) return 1;
		int[][][] visited = new int[n][m][2];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		visited[0][0][0] = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int wall = curr[2];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == 0 && visited[nr][nc][wall] == 0) {
					visited[nr][nc][wall] = visited[r][c][wall] + 1;
					if (nr == n - 1 && nc == m - 1) return visited[nr][nc][wall];
					queue.offer(new int[] {nr, nc, wall});
				}
				if (map[nr][nc] == 1 && wall == 0 && visited[nr][nc][1] == 0) {
					visited[nr][nc][1] = visited[r][c][wall] + 1;
					queue.offer(new int[] {nr, nc, 1});
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}