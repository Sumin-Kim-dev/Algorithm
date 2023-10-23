import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static char[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[m][n];
		int sr = -1;
		int sc = -1;
		int er = -1; 
		int ec = -1;
		for (int i = 0; i < m; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'C') {
					if (sr == -1 && sc == -1) {
						sr = i;
						sc = j;
					} else {
						er = i;
						ec = j;
					}
				}
			}
		}
		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		int[][][] visited = new int[m][n][4];
		Deque<int[]> deque = new ArrayDeque<>();
		for (int d = 0; d < 4; d++) {
			visited[sr][sc][d] = 1;
			deque.offerLast(new int[] {sr, sc, d});
		}
		while (!deque.isEmpty()) {
			int[] curr = deque.pollFirst();
			int cr = curr[0];
			int cc = curr[1];
			int cd = curr[2];
			// 직진하는 경우
			int nr = cr + dr[cd];
			int nc = cc + dc[cd];
			if (isIn(nr, nc) && map[nr][nc] != '*' && (visited[nr][nc][cd] == 0 || visited[nr][nc][cd] > visited[cr][cc][cd])) {
				visited[nr][nc][cd] = visited[cr][cc][cd];
				deque.offerFirst(new int[] {nr, nc, cd});
			}
			if (cr == sr && cc == sc) continue;
			// 거울을 두는 경우
			int nd = (cd + 1) % 4;
			nr = cr + dr[nd];
			nc = cc + dc[nd];
			if (isIn(nr, nc) && map[nr][nc] != '*' && visited[nr][nc][nd] == 0) {
				visited[nr][nc][nd] = visited[cr][cc][cd] + 1;
				deque.offerLast(new int[] {nr, nc, nd});
			}
			nd = (cd + 3) % 4;
			nr = cr + dr[nd];
			nc = cc + dc[nd];
			if (isIn(nr, nc) && map[nr][nc] != '*' && visited[nr][nc][nd] == 0) {
				visited[nr][nc][nd] = visited[cr][cc][cd] + 1;
				deque.offerLast(new int[] {nr, nc, nd});
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i : visited[er][ec]) {
			if (i < answer && i > 0) answer = i;
		}
		return answer - 1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < m && c >= 0 && c < n;
	}

}