package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1018 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int board[][] = new int[N][M];

		for (int i = 0; i < N; i++) {
			String boardRow = br.readLine();
			for (int j = 0; j < M; j++) {
				if (boardRow.charAt(j) == 'W')
					board[i][j] = 0;
				else
					board[i][j] = 1;
			}
		}

		int min = 32;

		for (int i = 0; i < N - 7; i++) {
			for (int j = 0; j < M - 7; j++) {
				int repaint = 0;
				for (int k = 0; k < 8; k++) {
					for (int l = 0; l < 8; l++) {
						repaint += ((k + l) % 2) ^ board[i + k][j + l];
					}
				}
				if (repaint > 32)
					repaint = 64 - repaint;
				if (repaint < min)
					min = repaint;
			}
		}
		bw.write("" + min);
		bw.close();
	}

}
