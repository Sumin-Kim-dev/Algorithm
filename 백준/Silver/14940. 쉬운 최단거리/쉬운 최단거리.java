import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] deltas = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
	static int n;
	static int m;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int startR = 0;
		int startC = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					startR = i;
					startC = j;
				}
			}
		}
		int[][] answer = bfs(startR, startC);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(answer[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static int[][] bfs(int startR, int startC) {
		int[][] answer = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) answer[i][j] = -1;
			}
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {startR, startC});
		answer[startR][startC] = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int d = 0; d < 4; d++) {
				int[] next = {curr[0] + deltas[0][d], curr[1] + deltas[1][d]};
				if (!isIn(next)) continue;
				if (answer[next[0]][next[1]] >= 0) continue;
				if (map[next[0]][next[1]] == 0)	continue;
				answer[next[0]][next[1]] = answer[curr[0]][curr[1]] + 1;
				queue.offer(next);
			}
		}
		return answer;
	}
	
	private static boolean isIn(int[] coord) {
		return coord[0] >= 0 && coord[0] < n && coord[1] >= 0 && coord[1] < m;
	}
}