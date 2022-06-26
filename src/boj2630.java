

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2630 {

	static int number[] = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int paper[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		number(paper);
		bw.write(number[0] + "\n" + number[1]);
		bw.close();
	}

	static void number(int paper[][]) {
		int N = paper.length;
		if (sum(paper) == 0 || sum(paper) == N * N) {
			number[0] += 1 - sum(paper) / N / N;
			number[1] += sum(paper) / N / N;
		}

		else {
			int paper00[][] = new int[N / 2][N / 2];
			int paper01[][] = new int[N / 2][N / 2];
			int paper10[][] = new int[N / 2][N / 2];
			int paper11[][] = new int[N / 2][N / 2];
			for (int i = 0; i < N / 2; i++) {
				paper00[i] = Arrays.copyOfRange(paper[i], 0, N / 2);
				paper01[i] = Arrays.copyOfRange(paper[i], N / 2, N);
				paper10[i] = Arrays.copyOfRange(paper[i + N / 2], 0, N / 2);
				paper11[i] = Arrays.copyOfRange(paper[i + N / 2], N / 2, N);
			}
			number(paper00);
			number(paper01);
			number(paper10);
			number(paper11);
		}
	}

	static int sum(int paper[][]) {
		int N = paper.length;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sum += paper[i][j];
		}
		return sum;
	}
}
