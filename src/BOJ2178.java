import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

	static int n, m, maze[][], dist[][];
	static int xAdd[] = { -1, 1, 0, 0 }, yAdd[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new int[n][m];
		dist = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++)
				maze[i][j] = s.charAt(j) - '0';
		}
		bw.write(min() + "");
		bw.close();
	}

	static int min() {

		Queue<int[]> queue = new LinkedList<>();
		int start[] = { 0, 0 };
		dist[0][0] = 1;
		queue.add(start);

		while (!queue.isEmpty()) {
			int curr[] = queue.poll();
			int x = curr[0], y = curr[1];
			for (int i = 0; i < 4; i++) {
				int newX = x + xAdd[i];
				int newY = y + yAdd[i];
				int newXY[] = { newX, newY };
				if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
					if (maze[newX][newY] == 1 && dist[newX][newY] == 0) {
						queue.add(newXY);
						dist[newX][newY] = dist[x][y] + 1;
					}
				}
			}
		}
		return dist[n - 1][m - 1];
	}
}
