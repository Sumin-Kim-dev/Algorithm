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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		List<int[]>[] adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			adj[v].add(new int[] {u, c});
		}
		PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
		long[] minDist = new long[n];
		Arrays.fill(minDist, 10_000_000_000L);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int city = Integer.parseInt(st.nextToken()) - 1;
			minDist[city] = 0;
			pq.offer(new long[] {city, 0});
		}
		while(!pq.isEmpty()) {
			long[] curr = pq.poll();
			int city = (int) curr[0];
			long dist = curr[1];
			if (minDist[city] < dist) continue;
			for (int[] edge : adj[city]) {
				int next = edge[0];
				if (minDist[city] + edge[1] < minDist[next]) {
					minDist[next] = minDist[city] + edge[1];
					pq.offer(new long[] {next, minDist[next]});
				}
			}
		}
		int maxIndex = -1;
		long max = 0;
		for (int i = 0; i < n; i++) {
			if (minDist[i] > max) {
				maxIndex = i;
				max = minDist[i];
			}
		}
		System.out.println(maxIndex + 1);
		System.out.println(max);
	}

}