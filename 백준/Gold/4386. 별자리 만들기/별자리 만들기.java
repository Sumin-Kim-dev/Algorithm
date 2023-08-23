import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int f;
		int t;
		double w;
		
		public Edge(int f, int t, double w) {
			this.f = f;
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static Edge[] edges;
	static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		double[][] points = new double[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i][0] = Double.parseDouble(st.nextToken());
			points[i][1] = Double.parseDouble(st.nextToken());
		}
		
		edges = new Edge[n * (n - 1) / 2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				edges[i * (i - 1) / 2 + j] = new Edge(i, j, d(points[i], points[j]));
			}
		}
		Arrays.sort(edges);
		
		set = new int[n];
		Arrays.fill(set, -1);
		double cost = 0;
		int cnt = 0;
		for (Edge edge : edges) {
			if (union(edge.f, edge.t)) {
				cost += edge.w;
				cnt++;
			}
			if (cnt == n - 1) break;
		}
		System.out.println(cost);
	}

	private static boolean union(int f, int t) {
		f = find(f);
		t = find(t);
		if (f == t) return false;
		if (-set[f] > -set[t]) {
			set[f] += set[t];
			set[t] = f;
		} else {
			set[t] += set[f];
			set[f] = t;
		}
		return true;
	}

	private static int find(int x) {
		if (set[x] < 0) return x;
		return set[x] = find(set[x]);
	}

	private static double d(double[] p1, double[] p2) {
		return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
	}

}