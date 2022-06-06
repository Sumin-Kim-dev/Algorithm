package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj14889 {

	static int N, s[][], team[];
	static int min = 40000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		s = new int[N][N];
		team = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer(0);
		bw.write(min + "");
		bw.close();
	}

	static void answer(int depth) {
		if (depth == N / 2) {
			if (scoreDiff(team) < min)
				min = scoreDiff(team);
			return;
		}

		// 스타트 팀에 포함된 마지막 팀원 번호 i 구하기(아무도 없으면 -1)
		int i = N - 1;
		while (i >= 0 && team[i] == 0)
			i--;
		for (int j = i + 1; j < N; j++) {
			team[j] = 1;
			answer(depth + 1);
			team[j] = 0;
		}
	}

	static int scoreDiff(int team[]) {
		int score = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				score += (s[i][j] + s[j][i]) * (team[i] + team[j] - 1);
			}
		}
		return score > 0 ? score : -score;
	}
}
