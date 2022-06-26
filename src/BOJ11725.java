

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11725 {

	static int parent[];
	static boolean check[];
	static ArrayList<Integer> connected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		connected = new ArrayList[n];
		for (int i = 0; i < n; i++)
			connected[i] = new ArrayList<Integer>();
		parent = new int[n];
		check = new boolean[n];
		int e1, e2;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			e1 = Integer.parseInt(st.nextToken());
			e2 = Integer.parseInt(st.nextToken());
			connected[e1 - 1].add(e2);
			connected[e2 - 1].add(e1);
		}
		findParent(1);
		for (int i = 1; i < n; i++)
			sb.append(parent[i]).append('\n');
		bw.write(sb.toString());
		bw.close();
	}

	static void findParent(int node) {
		if (!check[node - 1]) {
			check[node - 1] = true;
			for (int j = 0; j < connected[node - 1].size(); j++) {
				int next = connected[node - 1].get(j);
				if (!check[next - 1]) {
					parent[next - 1] = node;
					findParent(next);
				}
			}
		}
	}
}