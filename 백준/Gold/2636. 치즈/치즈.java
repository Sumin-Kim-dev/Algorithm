import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int x, y;
	static int[][] graph;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		graph = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < y; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
//		for (int i = 0; i < x; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		int time = 0;
		while (true){
			time += 1;
			cnt = bfs(0, 0);
			if(cnt == 0) {
				break;
			}
		}
		
		System.out.println(time - 1);
		System.out.println(list.get(list.size() - 2));
		list.clear();
		
	}
	
	static int bfs(int i, int j) {
		int cnt = 0;
		visited = new boolean[x][y];
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {i,j});
		visited[i][j] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int now_x = now[0];
			int now_y = now[1];
			for (int d = 0; d < 4; d++) {
				int nx = now_x + dx[d];
				int ny = now_y + dy[d];
				if(0 <= nx && nx < x && 0 <= ny && ny < y && visited[nx][ny] == false) {
					if(graph[nx][ny] == 0) {
						que.add(new int[] {nx, ny});
						visited[nx][ny] = true;
					}
					else {
						graph[nx][ny] = 0;
						visited[nx][ny] = true;
						cnt += 1;
					}
					
				}
			}
		}
		
		list.add(cnt);
		return cnt;
		
		
	}

}