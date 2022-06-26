import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

	final static int INF = 200001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());
		LinkedList<Edge>[] edge = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			edge[i] = new LinkedList<>();
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			edge[start].add(new Edge(end, Integer.parseInt(st.nextToken())));
		}
		int[] ans = dijkstra(edge, k - 1);
		for (int i = 0; i < v; i++) {
			if (ans[i] != INF)
				sb.append(ans[i]);
			if (ans[i] == INF)
				sb.append("INF");
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int[] dijkstra(LinkedList<Edge>[] edge, int k) {
		int[] dist = new int[edge.length];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		pq.offer(new Vertex(k, 0));
		dist[k] = 0;
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			// 이미 방문한 점은 스킵
			if (dist[curr.node] < curr.minDist)
				continue;
			// 이웃한 점 확인
			Iterator<Edge> iter = edge[curr.node].iterator();
			while (iter.hasNext()) {
				Edge e = iter.next();
				// 새 경로가 더 가깝다면 갱신
				if (dist[curr.node] + e.dist < dist[e.end]) {
					dist[e.end] = dist[curr.node] + e.dist;
					pq.offer(new Vertex(e.end, dist[e.end]));
				}
			}
		}
		return dist;
	}
}

class Vertex implements Comparable<Vertex> {

	final static int INF = 200001;

	int node, minDist;

	Vertex(int node, int minDist) {
		this.node = node;
		this.minDist = minDist;
	}

	public int compareTo(Vertex v) {
		return this.minDist - v.minDist;
	}
}

class Edge {
	int end, dist;

	Edge(int end, int dist) {
		this.end = end;
		this.dist = dist;
	}
}
