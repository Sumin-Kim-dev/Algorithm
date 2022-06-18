package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ10026 {

	static int n, picture[][], xAdd[] = { -1, 1, 0, 0 }, yAdd[] = { 0, 0, -1, 1 };
	static boolean checkNormal[][], checkRG[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		picture = new int[n][n];
		checkNormal = new boolean[n][n];
		checkRG = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				if (s.charAt(j) == 'R')
					picture[i][j] = -1;
				if (s.charAt(j) == 'G')
					picture[i][j] = 1;
				if (s.charAt(j) == 'B')
					picture[i][j] = 0;
			}
		}

		int count[] = count();
		bw.write(count[0] + " " + count[1]);
		bw.close();
	}

	static int[] count() {
		int normal = 0, RG = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!checkNormal[i][j]) {
					dfsNormal(i, j);
					normal++;
				}
				if (!checkRG[i][j]) {
					dfsRG(i, j);
					RG++;
				}
			}
		}
		return new int[] { normal, RG };
	}

	static void dfsNormal(int x, int y) {
		checkNormal[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int newX = x + xAdd[i];
			int newY = y + yAdd[i];
			if (newX >= 0 && newX < n & newY >= 0 && newY < n) {
				if (!checkNormal[newX][newY] && picture[newX][newY] == picture[x][y]) {
					dfsNormal(newX, newY);
				}
			}
		}
	}

	static void dfsRG(int x, int y) {
		checkRG[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int newX = x + xAdd[i];
			int newY = y + yAdd[i];
			if (newX >= 0 && newX < n & newY >= 0 && newY < n) {
				if (!checkRG[newX][newY]) {
					if (picture[newX][newY] == picture[x][y] || picture[newX][newY] == -picture[x][y]) {
						dfsRG(newX, newY);
					}
				}
			}
		}
	}
}
