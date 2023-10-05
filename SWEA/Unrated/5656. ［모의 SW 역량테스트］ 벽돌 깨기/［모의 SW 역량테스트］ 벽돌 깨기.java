import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int w;
	static int h;
	static int min;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int[][] map = new int[h + 1][w]; // 0행 : 가장 위의 벽돌 열번호 저장
			Arrays.fill(map[0], h + 1);
			min = h * w;
			for (int i = 1; i <= h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[0][j] == h + 1 && map[i][j] > 0) map[0][j] = i;
				}
			}
			backtracking(0, map);
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static void backtracking(int cnt, int[][] map) {
		if (cnt == n) {
			int curr = 0;
			for (int i = 0; i < w; i++) {
				curr += (h + 1 - map[0][i]);
			}
			min = Math.min(min, curr);
			return;
		}
		for (int i = 0; i < w; i++) {
			backtracking(cnt + 1, marble(i, map));
		}
	}

	private static int[][] marble(int c, int[][] map) {
		if (map[0][c] == h + 1) return map;
		int[][] after = copy(map);
		int r = after[0][c];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c, after[r][c]});
		after[r][c] = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			r = curr[0];
			c = curr[1];
			int num = curr[2];
			for (int k = 1; k < num; k++) {
				for (int d = 0; d < 4; d++) {
					int nr = r + k * dr[d];
					int nc = c + k * dc[d];
					if (!isIn(nr, nc)) continue;
					if (after[nr][nc] == 0) continue;
					queue.offer(new int[] {nr, nc, after[nr][nc]});
					after[nr][nc] = 0;
				}
			}
		}
		for (int i = 0; i < w; i++) {
			int j = h; // 떨어져서 쌓여야하는 위치
			int k = h; // 떨이지기 시작할 위치
			while (k > 0) {
				if (after[k][i] == 0) {
					k--;
					continue;
				}
				after[j][i] = after[k][i];
				j--;
				k--;
			}
			if (j > 0) after[0][i] = j + 1;
			for (; j > 0; j--) {
				after[j][i] = 0;
			}
		}
		return after;
	}

	private static boolean isIn(int r, int c) {
		return r >= 1 && r <= h && c >= 0 && c < w;
	}

	private static int[][] copy(int[][] map) {
		int[][] copy = new int[map.length][];
		for (int i = 0; i < map.length; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}
}