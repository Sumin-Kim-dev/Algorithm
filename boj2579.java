package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2579 {
	static int score[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		score = new int[n];
		for (int i = 0; i < n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		bw.write(total_score(n) + "");
		bw.close();
	}

	static int total_score(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return score[0];
		if (n == 2)
			return score[0] + score[1];
		int score_nMinus2 = 0;
		int score_nMinus1 = score[0];
		int score_n = score[0] + score[1], temp;
		for (int i = 3; i <= n; i++) {
			temp = score_n;
			score_n = max(score_nMinus2 + score[i - 2], score_nMinus1) + score[i - 1];
			score_nMinus2 = score_nMinus1;
			score_nMinus1 = temp;
		}
		return score_n;
	}

	static int max(int x, int y) {
		return x > y ? x : y;
	}
}
