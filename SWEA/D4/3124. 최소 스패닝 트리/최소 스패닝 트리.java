import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge> {
		int f;
		int t;
		int w;
		
		public Edge(int f, int t, int w) {
			this.f = f;
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int v;
	static Edge[] edges;
	static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			edges = new Edge[e];
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(from, to, w);
			}
			Arrays.sort(edges);
			sb.append('#').append(t).append(' ').append(kruskal()).append('\n');
		}
		System.out.println(sb);
	}

	private static long kruskal() {
		long min = 0;
		int cnt = 0;
		
		set = new int[v];
		Arrays.fill(set, -1);
		
		for (Edge edge : edges) {
			int f = edge.f;
			int t = edge.t;
			int w = edge.w;
			if (union(f, t)) {
				min += w;
				if (++cnt == v - 1) break;
			}
		}
		return min;
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return false;
		if (-set[x] > -set[y]) {
			set[x] += set[y];
			set[y] = x;
		} else {
			set[y] += set[x];
			set[x] = y;
		}
		return true;
	}

	private static int find(int x) {
		if (set[x] < 0) return x;
		return set[x] = find(set[x]);
	}

}