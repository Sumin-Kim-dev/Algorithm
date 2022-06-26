import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {

	static int tomato[][], m, n, count;
	static int xAdd[] = { -1, 1, 0, 0 }, yAdd[] = { 0, 0, -1, 1 };
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		tomato = new int[m][n];
		for (int j = 0; j < n; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1)
					queue.add(new int[] { i, j });
				if (tomato[i][j] == 0)
					count++;
			}
		}

		bw.write(date() + "");
		bw.close();
	}

	static int date() {

		int date = 0;
		// 이미 다 익었음
		if (count == 0)
			return 0;

		// bfs로 익는 날짜 구하기
		while (!queue.isEmpty()) {
			int loc[] = queue.poll();
			int x = loc[0], y = loc[1];
			for (int index = 0; index < 4; index++) {
				int newX = x + xAdd[index];
				int newY = y + yAdd[index];
				if (isIn(newX, newY) && tomato[newX][newY] == 0) {
					queue.add(new int[] { newX, newY });
					tomato[newX][newY] = tomato[x][y] + 1;
					if (tomato[newX][newY] > date)
						date = tomato[newX][newY];
					count--;
				}
			}
		}

		// 안 익은 토마토가 있음
		if (count != 0)
			return -1;

		return date - 1;
	}

	static boolean isIn(int x, int y) {
		boolean isInRangeX = x >= 0 && x < m;
		boolean isInRangeY = y >= 0 && y < n;
		return isInRangeX && isInRangeY && tomato[x][y] == 0;
	}
}
