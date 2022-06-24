package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11758 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] point = new int[3][2];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		int det = (point[0][0] - point[1][0]) * (point[2][1] - point[1][1]);
		det -= (point[2][0] - point[1][0]) * (point[0][1] - point[1][1]);
		int direction = 0;
		if (det > 0)
			direction = -1;
		if (det < 0)
			direction = 1;
		bw.write(direction + "");
		bw.close();
	}
}
