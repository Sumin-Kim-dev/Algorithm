import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	
	static int n;
	static int[][] map;
	static int remain;
	
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			remain = n * n;
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					if (str.charAt(j) == '*') {
						map[i][j] = -1;
						remain--;
					}
				}
			}
			setMap();
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] != 0) continue;
					count++;
					bfs(i, j, count + 1);
				}
			}
			sb.append("#" + t + " " + (count + remain) + "\n");
		}
		System.out.println(sb);
	}

	private static void setMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == -1) continue;
				for (int d = 0; d < 8; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (isIn(nr, nc) && map[nr][nc] == -1) {
						map[i][j] = 1;
						break;
					}
				}
			}
		}
	}
	
	private static void bfs(int i, int j, int group) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		map[i][j] = group;
		remain--;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 8; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == -1) continue;
				if (map[nr][nc] > 1) continue;
				if (map[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
				}
				map[nr][nc] = group;
				remain--;
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}