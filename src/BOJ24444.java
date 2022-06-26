import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ24444 {

	static LinkedList<Integer>[] edge;
	static int[] bfs;
	static int count = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		// 초기화
		bfs = new int[n];
		edge = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			edge[i] = new LinkedList<>();
		}

		// 간선 정보 연결 리스트에 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			edge[v1 - 1].add(v2 - 1);
			edge[v2 - 1].add(v1 - 1);
		}
		// 연결 리스트 정렬
		for (int i = 0; i < n; i++) {
			Collections.sort(edge[i]);
		}

		bfs(r - 1);
	}

	static void bfs(int r) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		Queue<Integer> queue = new LinkedList<>();
		queue.add(r);
		bfs[r] = count++;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Iterator<Integer> iter = edge[curr].iterator();
			while (iter.hasNext()) {
				int next = iter.next();
				if (bfs[next] == 0) {
					queue.add(next);
					bfs[next] = count++;
				}
			}
		}
		for (int i = 0; i < bfs.length; i++)
			sb.append(bfs[i]).append('\n');
		bw.write(sb.toString());
		bw.close();
	}
}
