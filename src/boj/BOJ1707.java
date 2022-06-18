package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			LinkedList<Integer>[] edge = new LinkedList[v];
			for (int j = 0; j < v; j++)
				edge[j] = new LinkedList<>();
			for (int j = 0; j < e; j++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				edge[v1 - 1].add(v2 - 1);
				edge[v2 - 1].add(v1 - 1);
			}
			sb.append(isBipartite(v, edge)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static String isBipartite(int v, LinkedList<Integer>[] edge) {
		Queue<Integer> queue = new LinkedList<>();
		// 아직 안 정해졌으면 0, 아니면 집합에 따라 1 또는 2
		int[] set = new int[v];

		for (int i = 0; i < v; i++) {
			// 이미 집합 정해졌으면 스킵
			if (set[i] != 0)
				continue;
			// 안 정해졌으면 1번 집합에 넣기
			set[i] = 1;
			queue.add(i);
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				Iterator<Integer> iter = edge[curr].iterator();
				while (iter.hasNext()) {
					int next = iter.next();
					if (set[next] == 0) {
						set[next] = 3 - set[curr];
						queue.add(next);
					}
					// 충돌 일어나는 경우
					if (set[next] == set[curr])
						return "NO";
				}
			}
		}
		return "YES";
	}
}
