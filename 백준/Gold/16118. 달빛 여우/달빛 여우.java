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
	
	public static final long INF = Long.MAX_VALUE;
	static int n;
	static List<int[]>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		int m = Integer.parseInt(st.nextToken());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int d = 2 * Integer.parseInt(st.nextToken());
			adj[a].add(new int[] {b, d});
			adj[b].add(new int[] {a, d});
		}
		long[] fox = fox();
		long[] wolf = wolf();
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (fox[i] < wolf[i]) answer++;
		}
		System.out.println(answer);
	}

	private static long[] fox() {
		long[] fox = new long[n];
		Arrays.fill(fox, INF);
		fox[0] = 0;
		PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
		pq.offer(new long[] {0, fox[0]});
		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int v = (int) curr[0];
			long d = curr[1];
			if (fox[v] < d) continue;
			for (int[] e : adj[v]) {
				if (fox[e[0]] > d + e[1]) {
					fox[e[0]] = d + e[1];
					pq.offer(new long[] {e[0], fox[e[0]]});
				}
			}
		}
		return fox;
	}

	private static long[] wolf() {
		long[] minDist = new long[2 * n];
		Arrays.fill(minDist, INF);
		minDist[0] = 0;
		PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
		pq.offer(new long[] {0, minDist[0]});
		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int v = (int) curr[0];
			long parity = curr[0] / n;
			long d = curr[1];
			if (minDist[v] < d) continue;
			v %= n;
			for (int[] e : adj[v]) {
				int t = e[1];
				if (parity == 0) t /= 2;
				else t *= 2;
				int nv = (int) (e[0] + n * (1 - parity));
				if (minDist[nv] > d + t) {
					minDist[nv] = d + t;
					pq.offer(new long[] {nv, minDist[nv]});
				}
			}
		}
		long[] wolf = new long[n];
		for (int i = 0; i < n; i++) {
			wolf[i] = Math.min(minDist[i], minDist[i + n]);
		}
		return wolf;
	}
}