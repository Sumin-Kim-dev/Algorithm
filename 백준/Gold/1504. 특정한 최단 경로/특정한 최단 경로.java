import java.io.*;
import java.util.*;

public class Main {
	
	class Node implements Comparable<Node>{
		int node, minDist;
		Node(int node, int minDist) {
			this.node = node;
			this.minDist = minDist;
		}
		public int compareTo(Node n) {
			return this.minDist - n.minDist;
		}
	}
	
	final int INF = Integer.MAX_VALUE;
	int n, e, v1, v2;
	int[][] adj;
	
	private void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		adj = new int[n][n];
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a][b] = adj[b][a] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken()) - 1;
		v2 = Integer.parseInt(st.nextToken()) - 1;
	}
	
	private int[] Dijkstra(int start) {
		int[] dist = new int[n];
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int currNode = curr.node;
			int currDist = curr.minDist;
			if(dist[currNode] < currDist)
				continue;
			for(int i = 0; i < n; i++) {
				if(adj[currNode][i] == 0)
					continue;
				if(dist[i] > currDist + adj[currNode][i]) {
					dist[i] = currDist + adj[currNode][i];
					pq.add(new Node(i, dist[i]));
				}
			}
		}
		return dist;
	}
	
	private void solution() throws IOException {
		input();
		int dist = INF;
		int[] dist1 = Dijkstra(v1), dist2 = Dijkstra(v2);
		if(dist1[0] != INF && dist1[v2] != INF && dist2[n-1] != INF)
			dist = dist1[0] + dist1[v2] + dist2[n-1];
		if(dist2[0] != INF && dist2[v1] != INF && dist1[n-1] != INF)
			if(dist > dist2[0] + dist2[v1] + dist1[n-1])
				dist = dist2[0] + dist2[v1] + dist1[n-1];
		if(dist == INF) dist = -1;
		print(dist);
	}
	
	private void print(int ans) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(ans+"");
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

}