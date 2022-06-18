package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15657 {

	static StringBuilder sb = new StringBuilder();
	static int m, n, arr[], seq[];
	static boolean check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		seq = new int[m];
		check = new boolean[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		dfs(0);
		bw.write(sb.toString());
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == m) {
			for (int i = 0; i < m; i++)
				sb.append(seq[i]).append(' ');
			sb.append('\n');
			return;
		}
		for (int i = 0; i < n; i++) {
			if (depth == 0 || seq[depth - 1] <= arr[i]) {
				seq[depth] = arr[i];
				dfs(depth + 1);
			}
		}
	}
}
