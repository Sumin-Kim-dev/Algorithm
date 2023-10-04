import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int k;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
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
		int[][][] visited = new int[n][m][k + 1];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		visited[0][0][0] = 1;
		int min = -1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int w = curr[2];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				int nw = w + map[nr][nc];
				if (nw > k) continue;
				if (visited[nr][nc][nw] > 0) continue;
				queue.offer(new int[] {nr, nc, nw});
				visited[nr][nc][nw] = visited[r][c][w] + 1;
				if (nr == n - 1 && nc == m - 1) {
					return visited[nr][nc][nw];
				}
			}
		}
		return min;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}