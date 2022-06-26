

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

	final static int[] xAdd = { -1, 1, 0, 0 }, yAdd = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = row.charAt(j) - '0';
		}
		bw.write(minDist(map, n, m) + "");
		bw.close();
	}

	static int minDist(int[][] map, int n, int m) {
		if (n == 1 && m == 1)
			return 1;
		Path[][] path = new Path[n][m];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, map[0][0] });
		path[0][0] = new Path(1, map[0][0]);
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0], y = curr[1], wall = curr[2];
			int dist = path[x][y].getDist(wall);
			for (int i = 0; i < 4; i++) {
				int newX = x + xAdd[i];
				int newY = y + yAdd[i];
				// ������ ��� ���� ����
				if (newX < 0 || newX >= n || newY < 0 || newY >= m)
					continue;
				// �̹� ���� �ѹ� �ν��µ� �� ���� ����� ���� ����
				if ((wall & map[newX][newY]) == 1)
					continue;
				// �ѹ��� �� �ͺ� ������ ���
				if (path[newX][newY] == null) {
					// �������� ������ ���
					if (newX == n - 1 && newY == m - 1)
						return dist + 1;
					int newWall = wall | map[newX][newY];
					queue.add(new int[] { newX, newY, newWall });
					path[newX][newY] = new Path(dist + 1, newWall);
					continue;
				}
				// �̹� �� �հ� �ͺ� ���������� ���� �� �հ� �� �� �ִ� ���
				if (!path[newX][newY].check0 && wall == 0 && map[newX][newY] == 0) {
					queue.add(new int[] { newX, newY, wall });
					path[newX][newY].setDist(dist + 1);
					continue;
				}
			}
		}
		return -1;
	}
}

class Path {
	int dist0, dist1;
	boolean check0;

	Path(int dist, int wall) {
		if (wall == 1) {
			dist1 = dist;
			check0 = false;
		}
		if (wall == 0) {
			dist0 = dist1 = dist;
			check0 = true;
		}
	}

	void setDist(int dist) {
		dist0 = dist;
		check0 = true;
	}

	int getDist(int wall) {
		if (wall == 1)
			return dist1;
		return dist0;
	}

	int minDist() {
		if (dist0 != 0)
			return dist0;
		return dist1;
	}
}
