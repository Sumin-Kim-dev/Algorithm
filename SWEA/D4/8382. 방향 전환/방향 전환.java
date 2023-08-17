import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int n = 100;
	static int[][] dx = {{-1, 1}, {0, 0}};
	static int[][] dy = {{0, 0}, {-1, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) + n;
			int y1 = Integer.parseInt(st.nextToken()) + n;
			int x2 = Integer.parseInt(st.nextToken()) + n;
			int y2 = Integer.parseInt(st.nextToken()) + n;
			sb.append("#" + t + " " + bfs(x1, y1, x2, y2) + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && y1 == y2) return 0;
		int[][][] visited = new int[2 * n + 1][2 * n + 1][2];
		visited[x1][y1][0] = visited[x1][y1][1] = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x1, y1, 0});
		queue.offer(new int[] {x1, y1, 1});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int k = curr[2];
			for (int d = 0; d < 2; d++) {
				int nx = x + dx[k][d];
				int ny = y + dy[k][d];
				if (nx == x2 && ny == y2) {
					return visited[x][y][k];
				}
				if (!isIn(nx, ny)) continue;
				if (visited[nx][ny][1 - k] > 0) continue;
				visited[nx][ny][1 - k] = visited[x][y][k] + 1;
				queue.offer(new int[] {nx, ny, 1 - k});
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x <= 2 * n && y >= 0 && y <= 2 * n;
	}

}