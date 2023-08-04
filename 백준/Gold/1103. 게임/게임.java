import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] remain;
	public static final int MAX = 100_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = str.charAt(j);
				if (c == 'H') map[i][j] = -1;
				else map[i][j] = c - '0';
			}
		}
		System.out.println(solution());
	}

	private static int solution() {
		visited = new boolean[n][m];
		remain = new int[n][m];
		int answer = dfs(0, 0);
		return answer >= MAX ? -1 : answer;
	}
	
	private static int dfs(int r, int c) {
		if (visited[r][c]) return remain[r][c] = MAX;
		if (remain[r][c] > 0) return remain[r][c];
		visited[r][c] = true;
		remain[r][c] = 1;
		for (int[] dir : deltas) {
			int nextR = r + dir[0] * map[r][c];
			int nextC = c + dir[1] * map[r][c];
			if (!isIn(nextR, nextC) || map[nextR][nextC] == -1) continue;
			remain[r][c] = Math.max(remain[r][c], dfs(nextR, nextC) + 1);
		}
		visited[r][c] = false;
		return remain[r][c];
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}