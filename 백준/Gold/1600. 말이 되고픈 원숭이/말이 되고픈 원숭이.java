import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static int w;
	static int h;
	static int[][] map;
	
	static int[][] monkey = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] horse = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		if (h == 1 && w == 1) return 0;
		int[][][] visited = new int[h][w][k + 1];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		visited[0][0][0] = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int use = curr[2];
			for (int d = 0; d < 4; d++) {
				int nr = r + monkey[d][0];
				int nc = c + monkey[d][1];
				if (nr == h - 1 && nc == w - 1) return visited[r][c][use];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == 1) continue;
				if (visited[nr][nc][use] > 0) continue;
				visited[nr][nc][use] = visited[r][c][use] + 1;
				queue.offer(new int[] {nr, nc, use});
			}
			if (use == k) continue;
			for (int d = 0; d < 8; d++) {
				int nr = r + horse[d][0];
				int nc = c + horse[d][1];
				if (nr == h - 1 && nc == w - 1) return visited[r][c][use];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == 1) continue;
				if (visited[nr][nc][use + 1] > 0) continue;
				visited[nr][nc][use + 1] = visited[r][c][use] + 1;
				queue.offer(new int[] {nr, nc, use + 1});
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

}