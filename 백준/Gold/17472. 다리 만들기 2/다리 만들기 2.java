import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken()) - 1;
			}
		}
		int count = countLand();
		int[][] graph = setGraph(count);
		
		int answer = 0;
		boolean[] visited = new boolean[count];
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		pq.offer(new int[] {0, 0});
		int conn = 0;
		while (!pq.isEmpty() && conn < count) {
			int[] curr = pq.poll();
			if (visited[curr[0]]) continue;
			visited[curr[0]] = true;
			answer += curr[1];
			conn++;
			for (int i = 0; i < count; i++) {
				if (visited[i]) continue;
				if (graph[curr[0]][i] == 0) continue;
				pq.offer(new int[] {i, graph[curr[0]][i]});
			}
		}
		System.out.println(conn == count ? answer : -1);
	}
	
	private static int[][] setGraph(int count) {
		int[][] graph = new int[count][count];
		for (int i = 0; i < n; i++) {
			int start = -1;
			for (int j = 0; j < m; j++) {
				if (map[i][j] >= 0) {
					if (start != -1 && map[i][start] != map[i][j]) {
						int s = map[i][start];
						int e = map[i][j];
						if (j - start > 2 && (graph[s][e] == 0 || graph[s][e] > j - start - 1)) {
							graph[s][e] = graph[e][s] = j - start - 1;
						}
					}
					start = j;
				}
			}
		}
		for (int j = 0; j < m; j++) {
			int start = -1;
			for (int i = 0; i < n; i++) {
				if (map[i][j] >= 0) {
					if (start != -1 && map[start][j] != map[i][j]) {
						int s = map[start][j];
						int e = map[i][j];
						if (i - start > 2 && (graph[s][e] == 0 || graph[s][e] > i - start - 1)) {
							graph[s][e] = graph[e][s] = i - start - 1;
						}
					}
					start = i;
				}
			}
		}
		return graph;
	}

	private static int countLand() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] >= -1) continue;
				dfs(i, j, count);
				count++;
			}
		}
		return count;
	}

	private static void dfs(int i, int j, int count) {
		map[i][j] = count;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (!isIn(nr, nc)) continue;
			if (map[nr][nc] >= -1) continue;
			dfs(nr, nc, count);
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}