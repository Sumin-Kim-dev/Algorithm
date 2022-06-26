

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj7569 {

	static int tomato[][][], m, n, h;
	static int xAdd[] = { -1, 1, 0, 0, 0, 0 }, yAdd[] = { 0, 0, -1, 1, 0, 0 }, zAdd[] = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		tomato = new int[m][n][h];
		for (int k = 0; k < h; k++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < m; i++)
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
			}
		}
		bw.write(date() + "");
		bw.close();
	}

	static int date() {
		int date[][][] = new int[m][n][h];
		Queue<int[]> queue = new LinkedList<>();

		// �̹� ���� �丶�� = 1������ �� ����
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < h; k++) {
					int loc[] = { i, j, k };
					if (tomato[i][j][k] == 1) {
						queue.add(loc);
						date[i][j][k] = 1;
					}
				}
			}
		}

		// bfs�� �ʹ� ��¥ ���ϱ�
		while (!queue.isEmpty()) {
			int loc[] = queue.poll();
			int x = loc[0], y = loc[1], z = loc[2];
			for (int index = 0; index < 6; index++) {
				int newX = x + xAdd[index];
				int newY = y + yAdd[index];
				int newZ = z + zAdd[index];
				int newLoc[] = { newX, newY, newZ };
				if (isIn(newX, newY, newZ) && date[newX][newY][newZ] == 0) {
					queue.add(newLoc);
					date[newX][newY][newZ] = date[x][y][z] + 1;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < h; k++) {
					if (tomato[i][j][k] != -1) {
						if (date[i][j][k] == 0)
							return -1;
						if (date[i][j][k] > ans)
							ans = date[i][j][k];
					}
				}
			}
		}
		return ans - 1;
	}

	static boolean isIn(int x, int y, int z) {
		boolean isInRangeX = x >= 0 && x < m;
		boolean isInRangeY = y >= 0 && y < n;
		boolean isInRangeZ = z >= 0 && z < h;
		return isInRangeX && isInRangeY && isInRangeZ && tomato[x][y][z] == 0;
	}
}
