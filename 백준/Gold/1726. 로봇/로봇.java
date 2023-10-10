import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int m;
	static int n;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] dir = {1, 3, 2, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int sd = dir[Integer.parseInt(st.nextToken()) - 1];
		
		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;
		int ed = dir[Integer.parseInt(st.nextToken()) - 1];
		
		System.out.println(bfs(sr, sc, sd, er, ec, ed));
	}

	private static int bfs(int sr, int sc, int sd, int er, int ec, int ed) {
		if (sr == er && sc == ec) {
			if (sd == ed) return 0;
			if ((sd ^ ed) == 2) return 2;
			return 1;
		}
		int[][][] visited = new int[m][n][4];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sr, sc, sd});
		visited[sr][sc][sd] = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cr = curr[0];
			int cc = curr[1];
			int cd = curr[2];
			
			int nr = cr + dr[cd];
			int nc = cc + dc[cd];
			for (int k = 0; k < 3; k++) {
				if (!isIn(nr, nc) || map[nr][nc] == 1) break;
				if (visited[nr][nc][cd] == 0) {
					if (nr == er && nc == ec && cd == ed) {
						return visited[cr][cc][cd];
					}
					queue.offer(new int[] {nr, nc, cd});
					visited[nr][nc][cd] = visited[cr][cc][cd] + 1;
				}
				nr += dr[cd];
				nc += dc[cd];
			}
			if (visited[cr][cc][(cd + 1) % 4] == 0) {
				if (cr == er && cc == ec && (cd + 1) % 4 == ed) {
					return visited[cr][cc][cd];
				}
				queue.offer(new int[] {cr, cc, (cd + 1) % 4});
				visited[cr][cc][(cd + 1) % 4] = visited[cr][cc][cd] + 1;
			}
			if (visited[cr][cc][(cd + 3) % 4] == 0) {
				if (cr == er && cc == ec && (cd + 3) % 4 == ed) {
					return visited[cr][cc][cd];
				}
				queue.offer(new int[] {cr, cc, (cd + 3) % 4});
				visited[cr][cc][(cd + 3) % 4] = visited[cr][cc][cd] + 1;
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < m && c >= 0 && c < n;
	}

}