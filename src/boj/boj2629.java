package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2629 {

	static int able[][];
	static int weight[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		weight = new int[2 * n];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			weight[2 * i] = Integer.parseInt(st.nextToken());
			weight[2 * i + 1] = weight[2 * i];
			sum += weight[2 * i];
		}
		able = new int[2 * n + 1][sum + 40001];
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int w;
		for (int i = 0; i < m; i++) {
			w = Integer.parseInt(st.nextToken());
			sb.append(toChar(able(2 * n, w + sum))).append(' ');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int able(int m, int w) {
		if (w == 0)
			return 1;
		if (m == 0 || w < 0)
			return -1;
		if (able[m][w] != 0)
			return able[m][w];
		able[m][w] = able(m - 1, w);
		if (able(m - 1, w - weight[m - 1]) >= able[m][w])
			able[m][w] = able(m - 1, w - weight[m - 1]);
		return able[m][w];
	}

	static char toChar(int able) {
		if (able == 1)
			return 'Y';
		else
			return 'N';
	}
}
