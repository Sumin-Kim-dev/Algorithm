import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	static int[][] before;
	static int count;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		before = new int[n][m];
		count = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) count++;
			}
		}
		
		int t = 0;
		int answer = 0;
		while (count > 0) {
			t++;
			before = new int[n][m];
			for (int i = 0; i < n; i++) {
				before[i] = Arrays.copyOf(map[i], m);
			}
			answer = bfs();
			map = before;
		}
		System.out.println(t);
		System.out.println(answer);
	}

	private static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0});
		map[0][0] = 2;
		int melt = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
					map[nr][nc] = 2;
				} else if (map[nr][nc] == 1) {
					map[nr][nc] = 3;
					before[nr][nc] = 0;
					melt++;
					count--;
				}
			}
		}
		return melt;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}