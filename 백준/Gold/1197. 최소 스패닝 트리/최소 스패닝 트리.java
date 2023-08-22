import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int s;
		int t;
		int w;
		
		public Edge(int s, int t, int w) {
			this.s = s;
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static PriorityQueue<Edge> edges = new PriorityQueue<>();
	static int v;
	static int[] p;
	static int[] r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		while (e-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges.offer(new Edge(s, t, w));
		}
		
		makeSet();
		
		int cnt = 0;
		int min = 0;
		while (!edges.isEmpty() && cnt < v - 1) {
			Edge edge = edges.poll();
			if (union(edge.s, edge.t)) {
				min += edge.w;
				cnt++;
			}
		}
		System.out.println(min);
	}

	private static void makeSet() {
		p = new int[v];
		r = new int[v];
		for (int i = 0; i < v; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return false;
		if (r[x] < r[y]) {
			r[y] += r[x];
			p[x] = y;
		} else {
			r[x] += r[y];
			p[y] = x;
		}
		return true;
	}

	private static int find(int x) {
		if (x == p[x]) return p[x];
		return p[x] = find(p[x]);
	}

}