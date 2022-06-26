

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

	static int n, m, connected[][];
	static boolean check[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		connected = new int[n][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			connected[v1 - 1][v2 - 1] = connected[v2 - 1][v1 - 1] = 1;
		}
		check = new boolean[n];
		dfs(v - 1);
		sb.append('\n');

		check = new boolean[n];
		bfs(v - 1);

		bw.write(sb.toString());
		bw.close();
	}

	static void dfs(int v) {
		sb.append(v + 1).append(' ');
		check[v] = true;
		for (int i = 0; i < n; i++) {
			if (!check[i] && connected[v][i] == 1)
				dfs(i);
		}
	}

	static void bfs(int v) {
		sb.append(v + 1).append(' ');
		check[v] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			for (int i = 0; i < n; i++) {
				if (!check[i] && connected[index][i] == 1) {
					check[i] = true;
					queue.add(i);
					sb.append(i + 1).append(' ');
				}
			}
		}
	}
}
