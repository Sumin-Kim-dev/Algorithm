package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9465 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				sticker[0][j] = Integer.parseInt(st1.nextToken());
				sticker[1][j] = Integer.parseInt(st2.nextToken());
			}
			sb.append(maxScore(sticker, n)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int maxScore(int[][] sticker, int n) {
		int[] up = new int[n + 1], down = new int[n + 1];
		up[n - 1] = sticker[0][n - 1];
		down[n - 1] = sticker[1][n - 1];
		for (int i = n - 2; i >= 0; i--) {
			up[i] = sticker[0][i] + max(down[i + 1], up[i + 2], down[i + 2]);
			down[i] = sticker[1][i] + max(up[i + 1], up[i + 2], down[i + 2]);
		}
		return up[0] > down[0] ? up[0] : down[0];
	}

	static int max(int a, int b, int c) {
		if (a > b)
			return a > c ? a : c;
		return b > c ? b : c;
	}
}
