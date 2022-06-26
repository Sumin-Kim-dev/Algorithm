

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ24480 {

	static LinkedList<Integer>[] edge;
	static int[] dfs;
	static int count = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		// �ʱ�ȭ
		dfs = new int[n];
		edge = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			edge[i] = new LinkedList<>();
		}

		// ���� ���� ���� ����Ʈ�� �Է�
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			edge[v1 - 1].add(v2 - 1);
			edge[v2 - 1].add(v1 - 1);
		}
		// ���� ����Ʈ ����
		for (int i = 0; i < n; i++) {
			Collections.sort(edge[i], Collections.reverseOrder());
		}

		dfs(r - 1);

		// ���
		for (int i = 0; i < n; i++) {
			sb.append(dfs[i]).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void dfs(int r) {
		dfs[r] = count++;
		Iterator<Integer> iter = edge[r].iterator();
		while (iter.hasNext()) {
			int next = iter.next();
			if (dfs[next] == 0)
				dfs(next);
		}
	}
}
