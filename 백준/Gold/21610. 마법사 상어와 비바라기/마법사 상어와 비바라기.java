import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] graph;
	static int d, s;
	static List<int[] > list;
	static Queue<int[] > cloud = new LinkedList<>();
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			list.add(new int[] {d, s});
		}
		
		// 처음 구름 추가
		cloud.add(new int[] {n - 1, 0});
		cloud.add(new int[] {n - 1, 1});
		cloud.add(new int[] {n - 2, 0});
		cloud.add(new int[] {n - 2, 1});
		
		for (int i = 0; i < m; i++) {
			visited = new boolean[n][n];
			int[] ds = list.get(i);
//			System.out.println(ds[0] + " " + ds[1]);
			move_cloud(ds[0], ds[1]);
			add_water();
			find_cloud();
		}
		
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer += graph[i][j];
			}
		}
		System.out.println(answer);
		

	}
	
	static void move_cloud(int d, int s) {
		int size = cloud.size();
		for (int i = 0; i < size; i++) {
			int[] now = cloud.poll();
			int x = now[0];
			int y = now[1];
			int nx = (x + dx[d] * s) % n;
			if(nx < 0) nx += n;
			int ny = (y + dy[d] * s) % n;
			if(ny < 0) ny += n;
			graph[nx][ny] += 1;
			visited[nx][ny] = true;
		}
		cloud.clear();
	}
	
	static void add_water() {
		int[][] new_graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 만약 현재 칸이 구름이라면
				if(visited[i][j] == true) {
					// 대각선을 체크해야함
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						// 만약 대각선이라면
						if(d % 2 == 1) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (0 <= nx && nx < n && 0 <= ny && ny < n) {
								if (graph[nx][ny] > 0 ) {
									cnt ++;
								}
							}
						}
					}
					new_graph[i][j] += cnt;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] += new_graph[i][j];
			}
		}
	}
	
	static void find_cloud() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(graph[i][j] >= 2 && visited[i][j] == false) {
					cloud.add(new int[] {i, j});
					graph[i][j] -= 2;
				}
			}
		}
	}
}