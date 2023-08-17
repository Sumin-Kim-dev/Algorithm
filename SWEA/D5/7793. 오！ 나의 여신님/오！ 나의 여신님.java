import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int m;
	static char[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			int r = 0;
			int c = 0;
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 'S') {
						r = i;
						c = j;
					}
				}
			}
			int answer = bfs(r, c);
			sb.append('#').append(t).append(' ').append(answer > 0 ? String.valueOf(answer) : "GAME OVER").append('\n');
		}
		System.out.println(sb);
	}

	private static int bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '*') queue.offer(new int[] {i, j});
			}
		}
		int t = 0;
		int possible = 1;
		while (!queue.isEmpty() && possible > 0) {
			t++;
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				int cr = curr[0];
				int cc = curr[1];
				for (int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (!isIn(nr, nc)) continue;
					if (map[cr][cc] == 'S' && map[nr][nc] == 'D') return t;
					if (map[nr][nc] == '.' || map[nr][nc] == 'S' && map[cr][cc] == '*') {
						if (map[nr][nc] == 'S') possible--;
						map[nr][nc] = map[cr][cc];
						if (map[nr][nc] == 'S') possible++;
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}