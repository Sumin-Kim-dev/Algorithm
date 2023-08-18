import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r;
	static int c;
	static char[][] map;
	static Queue<int[]> queue;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		int sr = 0;
		int sc = 0;
		queue = new ArrayDeque<>();
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '*') {
					queue.offer(new int[] {i, j, '*'});
					continue;
				}
				if (map[i][j] == 'S') {
					sr = i;
					sc = j;
				}
			}
		}
		int answer = bfs(sr, sc);
		System.out.println(answer > 0 ? answer : "KAKTUS");
	}

	private static int bfs(int sr, int sc) {
		queue.offer(new int[] {sr, sc, 'S'});
		int possible = 1;
		int t = 0;
		while (!queue.isEmpty() && possible > 0) {
			t++;
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				int r = curr[0];
				int c = curr[1];
				int state = curr[2];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!isIn(nr, nc)) continue;
					if (map[nr][nc] == 'X') continue;
					if (state == 'S' && map[nr][nc] == 'D') return t;
					if (state == 'S' && map[nr][nc] == '.') {
						map[nr][nc] = 'S';
						queue.offer(new int[] {nr, nc, 'S'});
						possible++;
						continue;
					}
					if (state == '*' && map[nr][nc] != 'D' && map[nr][nc] != '*') {
						if (map[nr][nc] == 'S') possible--;
						map[nr][nc] = '*';
						queue.offer(new int[] {nr, nc, '*'});
						continue;
					}
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < r && nc >= 0 && nc < c;
	}

}