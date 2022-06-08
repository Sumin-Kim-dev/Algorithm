package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj11049 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int size[] = new int[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			size[i] = Integer.parseInt(st.nextToken());
			if (i == n - 1)
				size[i + 1] = Integer.parseInt(st.nextToken());
		}
		bw.write(minTimes(size, n) + "");
		bw.close();
	}

	static int minTimes(int size[], int n) {
		int minTimes[][] = new int[n][n];
		int candidate;
		for (int j = 1; j < n; j++) {
			for (int i = 0; i < n - j; i++) {
				minTimes[i][i + j] = minTimes[i][i + j - 1] + size[i] * size[i + j] * size[i + j + 1];
				for (int k = j - 2; k >= 0; k--) {
					candidate = minTimes[i][i + k] + minTimes[i + k + 1][i + j]
							+ size[i] * size[i + k + 1] * size[i + j + 1];
					if (candidate < minTimes[i][i + j])
						minTimes[i][i + j] = candidate;
				}
			}
		}
		return minTimes[0][n - 1];
	}
}
