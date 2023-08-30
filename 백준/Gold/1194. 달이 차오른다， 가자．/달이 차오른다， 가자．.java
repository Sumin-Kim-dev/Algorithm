import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		int sr = 0;
		int sc = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0') {
					sr = i;
					sc = j;
				}
			}
		}
		int[][][] visited = new int[n][m][1 << 6];
		visited[sr][sc][0] = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sr, sc, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int key = curr[2];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextKey = key;
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if (map[nr][nc] == '#') continue;
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					if ((key & (1 << (map[nr][nc] - 'A'))) == 0) continue;
				}
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					nextKey = key | (1 << (map[nr][nc] - 'a'));
				}
				if (map[nr][nc] == '1') {
					System.out.println(visited[r][c][key]);
					return;
				}
				if (visited[nr][nc][nextKey] > 0) continue;
				visited[nr][nc][nextKey] = visited[r][c][key] + 1;
				queue.offer(new int[] {nr, nc, nextKey});
			}
		}
		System.out.println(-1);
	}

}