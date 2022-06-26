

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1780 {

	static int number[] = new int[3];

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
		bw.write(number[0] + "\n" + number[1] + "\n" + number[2]);
		bw.close();
	}

	static void number(int paper[][]) {
		int N = paper.length;
		if (isEqual(paper) != DEFAULT)
			number[isEqual(paper) + 1]++;

		else {
			int split[][][] = new int[9][N / 3][N / 3];
			for (int i = 0; i < N / 3; i++) {
				for (int j = 0; j < 9; j++) {
					split[j][i] = Arrays.copyOfRange(paper[i + (j / 3) * N / 3], (j % 3) * N / 3, (j % 3 + 1) * N / 3);
				}
			}
			for (int j = 0; j < 9; j++)
				number(split[j]);
		}
	}

	static final int DEFAULT = -2;

	static int isEqual(int paper[][]) {
		int N = paper.length;
		int x = paper[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				if (paper[i][j] != x)
					return DEFAULT;
		}
		return x;
	}
}
