package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj3009 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int points[][] = new int[4][2];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int j = 0; j < 2; j++) {
			if (points[0][j] == points[1][j])
				points[3][j] = points[2][j];
			if (points[0][j] == points[2][j])
				points[3][j] = points[1][j];
			if (points[1][j] == points[2][j])
				points[3][j] = points[0][j];

			bw.write(points[3][j] + " ");
		}
		bw.close();
	}
}