import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 100_000_000;
	
	static int n;
	static List<int[]>[] adj;
	static int[] minDist;
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		int m = Integer.parseInt(st.nextToken());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[s].add(new int[] {e, w});
			adj[e].add(new int[] {s, w});
		}
		minDist = new int[n];
		table = new int[n][n];
		for (int i = 0; i < n; i++) {
			dijkstra(i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) sb.append('-');
				else sb.append(table[i][j] + 1);
				sb.append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void dijkstra(int i) {
		Arrays.fill(minDist, INF);
		minDist[i] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		pq.offer(new int[] {i, 0});
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int c = curr[0];
			int d = curr[1];
			if (minDist[c] < d) continue;
			for (int[] e : adj[c]) {
				if (minDist[e[0]] <= minDist[c] + e[1]) continue;
				minDist[e[0]] = minDist[c] + e[1];
				pq.offer(new int[] {e[0], minDist[e[0]]});
				if (c == i) table[i][e[0]] = e[0];
				else table[i][e[0]] = table[i][c];
			}
		}
	}
}