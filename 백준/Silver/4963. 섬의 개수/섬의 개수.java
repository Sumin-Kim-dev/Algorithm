import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int w;
	static int h;
	static int[][] map;
	
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) break;
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(count());
		}
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] != 1) continue;
				cnt++;
				bfs(i, j, cnt + 1);
			}
		}
		return cnt;
	}

	private static void bfs(int i, int j, int cnt) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		map[i][j] = cnt;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 8; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] != 1) continue;
				queue.offer(new int[] {nr, nc});
				map[nr][nc] = cnt;
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

}