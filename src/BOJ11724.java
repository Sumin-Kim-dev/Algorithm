

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {

	static ArrayList<Integer> connected[];
	static boolean check[];
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		connected = new ArrayList[n];
		check = new boolean[n];
		for (int i = 0; i < n; i++)
			connected[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			connected[v1 - 1].add(v2 - 1);
			connected[v2 - 1].add(v1 - 1);
		}
		bw.write(count() + "");
		bw.close();
	}

	static int count() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!check[i]) {
				dfs(i);
				count++;
			}
		}
		return count;
	}

	static void dfs(int index) {
		for (int i = 0; i < connected[index].size(); i++) {
			int next = connected[index].get(i);
			if (!check[next]) {
				check[next] = true;
				dfs(next);
			}
		}
	}
}
