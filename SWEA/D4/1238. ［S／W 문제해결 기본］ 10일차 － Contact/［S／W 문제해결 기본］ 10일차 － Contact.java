import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static final int N = 100;
	static int[] dist;
	static boolean[][] adj; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int edges = Integer.parseInt(st.nextToken()) / 2;
			int start = Integer.parseInt(st.nextToken()) - 1;
			dist = new int[N + 1];
			adj = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			while (edges-- > 0) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				adj[from][to] = true;
			}
			sb.append('#').append(t).append(' ').append(bfs(start)).append('\n');
		}
		System.out.println(sb);
	}

	private static int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		dist[start] = 1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int i = 0; i < N; i++) {
				if (dist[i] == 0 && adj[curr][i]) {
					dist[i] = dist[curr] + 1;
					queue.offer(i);
				}
			}
		}
		int max = N;
		for (int i = N - 1; i >= 0; i--) {
			if (dist[i] > dist[max]) {
				max = i;
			}
		}
		return max + 1;
	}

}