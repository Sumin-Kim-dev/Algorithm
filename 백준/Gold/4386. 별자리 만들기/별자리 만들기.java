import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point implements Comparable<Point> {
		int i;
		double dist;
		
		public Point(int i, double dist) {
			this.i = i;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		double[][] points = new double[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i][0] = Double.parseDouble(st.nextToken());
			points[i][1] = Double.parseDouble(st.nextToken());
		}
		
		double[][] dist = new double[n][n];
		for (int i = 0; i < n; i++) {
			dist[i][i] = 10000;
			for (int j = 0; j < i; j++) {
				dist[i][j] = dist[j][i] = d(points[i], points[j]);
			}
		}
		
		double cost = 0;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		double[] minDist = new double[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(minDist, 10000);
		pq.offer(new Point(0, 0));
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			if (visited[p.i]) continue;
			minDist[p.i] = p.dist;
			visited[p.i] = true;
			cost += p.dist;
			for (int i = 0; i < n; i++) {
				if (dist[p.i][i] < minDist[i]) {
					minDist[i] = dist[p.i][i];
					pq.offer(new Point(i, minDist[i]));
				}
			}
		}
		System.out.println(cost);
	}

	private static double d(double[] p1, double[] p2) {
		return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
	}

}