import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Vertex implements Comparable<Vertex> {
		int e;
		long w;
		
		public Vertex(int e, long w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int n;
	static int[][] points;
	static PriorityQueue<Vertex> vertices;
	static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			points = new int[n][2];
			for (int j = 0; j < 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 0; i < n; i++) {
					points[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			vertices = new PriorityQueue<>();
			double e = Double.parseDouble(br.readLine());
			long min = (long) (e * prim() + 0.5);
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static long prim() {
		long min = 0;
		boolean[] visited = new boolean[n];
		long[] minEdge = new long[n];
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0;
		vertices.offer(new Vertex(0, minEdge[0]));
		
		while (!vertices.isEmpty()) {
			Vertex vertex = vertices.poll();
			int curr = vertex.e;
			if (visited[curr]) continue;
			visited[curr] = true;
			min += vertex.w;
			for (int i = 0; i < n; i++) {
				if (visited[i]) continue;
				long d = distance(points[i], points[curr]);
				if (minEdge[i] > d) {
					minEdge[i] = d;
					vertices.offer(new Vertex(i, minEdge[i]));
				}
			}
		}
		return min;
	}

	private static long distance(int[] p1, int[] p2) {
		return 1L * (p1[0] - p2[0]) * (p1[0] - p2[0]) + 1L * (p1[1] - p2[1]) * (p1[1] - p2[1]);
	}

}