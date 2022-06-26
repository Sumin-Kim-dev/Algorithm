

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1520 {

	static int m, n, map[][], path[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		path = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				path[i][j] = -1; // ���� �� ���� ���� -1�� �ʱ�ȭ
			}
		}
		bw.write(path(m - 1, n - 1) + "");
		bw.close();
	}

	static int path(int i, int j) {
		if (i == 0 && j == 0)
			return 1;
		if (path[i][j] != -1)
			return path[i][j];
		path[i][j] = 0;
		if (i > 0 && map[i - 1][j] > map[i][j])
			path[i][j] += path(i - 1, j);
		if (j > 0 && map[i][j - 1] > map[i][j])
			path[i][j] += path(i, j - 1);
		if (i < m - 1 && map[i + 1][j] > map[i][j])
			path[i][j] += path(i + 1, j);
		if (j < n - 1 && map[i][j + 1] > map[i][j])
			path[i][j] += path(i, j + 1);
		return path[i][j];
	}
}
