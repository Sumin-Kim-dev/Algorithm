import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	
	static int n;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 1) continue;
				cnt++;
				bfs(i, j, cnt + 1);
			}
		}
		System.out.println(cnt);
		
		int[] res = new int[cnt];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 1) res[map[i][j] - 2]++;
			}
		}
		Arrays.sort(res);
		for (int a : res) {
			System.out.println(a);
		}
	}
	
	private static void bfs(int i, int j, int cnt) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		map[i][j] = cnt;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (!check(nr, nc)) continue;
				if (map[nr][nc] != 1) continue;
				queue.offer(new int[] {nr, nc});
				map[nr][nc] = cnt;
			}
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}