import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge> {
		int t;
		int w;
		
		public Edge(int t, int w) {
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int v;
	static List<Edge>[] adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine()) - 1;
		adj = new ArrayList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new ArrayList<>();
		}
		while (e-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, w));
		}
		int[] answer = dijkstra(k);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < v; i++) {
			if (answer[i] == INF) sb.append("INF");
			else sb.append(answer[i]);
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static int[] dijkstra(int k) {
		boolean[] visited = new boolean[v];
		int[] minDist = new int[v];
		Arrays.fill(minDist, INF);
		minDist[k] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(k, minDist[k]));
		
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			if (visited[curr.t]) continue;
			visited[curr.t] = true;
			for (Edge next : adj[curr.t]) {
				if (visited[next.t]) continue;
				if (minDist[next.t] > minDist[curr.t] + next.w) {
					minDist[next.t] = minDist[curr.t] + next.w;
					pq.offer(new Edge(next.t, minDist[next.t]));
				}
			}
		}
		
		return minDist;
	}

}