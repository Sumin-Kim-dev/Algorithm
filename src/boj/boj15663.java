package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15663 {

	static int n, m, arr[], seq[];
	static boolean visit[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		dfs(0);
		print();
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		seq = new int[m];
		visit = new boolean[n];
	}

	static void dfs(int depth) {
		if (depth == m) {
			for (int i = 0; i < m; i++)
				sb.append(seq[i]).append(' ');
			sb.append('\n');
			return;
		}
		for (int i = 0; i < n; i++) {
			if (depth == 0 || !visit[i]) {
				visit[i] = true;
				if (seq[depth] != arr[i]) {
					seq[depth] = arr[i];
					dfs(depth + 1);
				}
				visit[i] = false;
			}
		}
		seq[depth] = 0;
	}

	static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.close();
	}
}
