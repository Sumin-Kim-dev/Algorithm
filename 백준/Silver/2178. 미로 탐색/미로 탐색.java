import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			if (r == n - 1 && c == m - 1) break;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!check(nr, nc)) continue;
				if (map[nr][nc] == 0) continue;
				if (visited[nr][nc] > 0) continue;
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = visited[r][c] + 1;
			}
		}
		// 도착 지점이 최소 보장!
		System.out.println(visited[n - 1][m - 1]);
	}
	
	// 내부?
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < m;
	}
}