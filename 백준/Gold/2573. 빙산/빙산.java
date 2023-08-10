import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	static int[][] cnt;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	static boolean check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		
		while(true) {
			visited = new boolean[n][m];
			cnt = new int[n][m];
			List<Integer> list = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(graph[i][j] != 0 && visited[i][j] == false) {
						list.add(bfs(i, j));
					}
				}
			}
			
			// melt
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(graph[i][j] > 0) {
						graph[i][j] -= cnt[i][j];
						graph[i][j] = Math.max(0, graph[i][j]);
					}
				}
			}
			
			if (list.size() == 0) {
				check = true;
				break;
			}
			if(list.size() >= 2) {
				break;
			}
			
			answer += 1;
		}
		if(check == true) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
		
		
	}

	static int bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
					if(visited[nx][ny] == false && graph[nx][ny] != 0) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}else if(graph[nx][ny] == 0) {
						cnt[x][y] += 1;
					}
				}
			}
		}
		return 1;
	}

}