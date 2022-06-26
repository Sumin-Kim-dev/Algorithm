

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ17070 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] house = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bw.write(dp(house, n) + "");
		bw.close();
	}

	static int dp(int[][] house, int n) {
		int[][] row = new int[n][n], column = new int[n][n], diagonal = new int[n][n];
		row[0][1] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 2; j < n; j++) {
				if (house[i][j] == 0)
					row[i][j] = row[i][j - 1] + diagonal[i][j - 1];
				if (i > 0 && house[i][j] == 0)
					column[i][j] = column[i - 1][j] + diagonal[i - 1][j];
				if (i > 0 && house[i][j] == 0 && house[i][j - 1] == 0 && house[i - 1][j] == 0)
					diagonal[i][j] = row[i - 1][j - 1] + column[i - 1][j - 1] + diagonal[i - 1][j - 1];
			}
		}
		return row[n - 1][n - 1] + column[n - 1][n - 1] + diagonal[n - 1][n - 1];
	}
}
