

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj14500 {

	static int n, m;
	static int[][] nums;
	static ArrayList<Integer> tetro = new ArrayList<>();

	static final int[][][] TETRO = { { { 1, 0 }, { 2, 0 }, { 3, 0 } }, { { 0, 1 }, { 0, 2 }, { 0, 3 } },
			{ { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 } },
			{ { 1, 0 }, { 2, 0 }, { 2, 1 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 } }, { { 0, 1 }, { 0, 2 }, { 1, 0 } },
			{ { 0, 1 }, { 0, 2 }, { -1, 2 } }, { { 1, 0 }, { 2, 0 }, { 2, -1 } }, { { 0, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 1, 0 }, { 1, -1 }, { 2, -1 } },
			{ { 0, 1 }, { 1, 0 }, { 1, -1 } }, { { 1, 0 }, { 2, 0 }, { 1, 1 } }, { { 1, 0 }, { 2, 0 }, { 1, -1 } },
			{ { 1, 0 }, { 1, -1 }, { 1, 1 } }, { { 0, 1 }, { 0, 2 }, { 1, 1 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nums = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				nums[i][j] = Integer.parseInt(st.nextToken());
		}
		bw.write(tetro() + "");
		bw.close();
	}

	static int tetro() {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int sum = tetro(i, j);
				if (sum > max)
					max = sum;
			}
		}
		return max;
	}

	static int tetro(int i, int j) {
		int max = 0;
		for (int index = 0; index < 19; index++) {
			int sum = nums[i][j];
			for (int count = 0; count < 3; count++) {
				boolean isIn = isIn(i + TETRO[index][count][0], j + TETRO[index][count][1]);
				if (!isIn) {
					sum = 0;
					break;
				}
				if (isIn)
					sum += nums[i + TETRO[index][count][0]][j + TETRO[index][count][1]];
			}
			if (sum > max)
				max = sum;
		}
		return max;
	}

	static boolean isIn(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}
