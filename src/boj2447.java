

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2447 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char star[][] = star(N);
		for (int i = 0; i < N; i++) {
			System.out.println(String.valueOf(star[i]));
		}
	}

	static char[][] star(int N) {
		char star[][] = new char[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(star[i], ' ');
		}
		if (N == 1) {
			star[0][0] = '*';
			return star;
		}
		char starN3[][] = star(N / 3);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!(i >= N / 3 && i < 2 * N / 3 && j >= N / 3 && j < 2 * N / 3)) {
					star[i][j] = starN3[i % (N / 3)][j % (N / 3)];
				}
			}
		}
		return star;
	}
}