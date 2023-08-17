import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static char[][] graph;
	// 오른쪽위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static boolean[][] visited;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new char[r][c];
		visited = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				graph[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < r; i++) {
			if(dfs(i, 0) == true) {
				answer ++;
			}
		}
		
		System.out.println(answer);

	}
	
	private static boolean dfs(int x, int y) {
		if(y == c - 1) {
			return true;
		}
		
		for (int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(0 <= nx && nx < r && 0 <= ny && ny < c) {
				if(graph[nx][ny] != 'x' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if(dfs(nx, ny)) {
						return true;
					}
				}
			}
		}
		
		return false;
		
	}

}
