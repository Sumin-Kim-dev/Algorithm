import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<int[]>[] adj;
	static int n;
	static int logn;
	static int[] depth;
	static int[][] parents;
	static long[][] dist;
	static int[][] maxEdges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		logn = 31 - Integer.numberOfLeadingZeros(n);
		int q = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[f].add(new int[] {t, w});
			adj[t].add(new int[] {f, w});
		}
		makeTree();
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if (a == b) {
				sb.append(0).append('\n');
				continue;
			}
			int[] disconnect = findMax(u, v);
			boolean isCutA = isCut(a, disconnect[0]);
			boolean isCutB = isCut(b, disconnect[0]);
			if (isCutA ^ isCutB) {
				if (isCutA) sb.append(dist(a, disconnect[1]) + w + dist(b, u + v - disconnect[1]));
				else sb.append(dist(b, disconnect[1]) + w + dist(a, u + v - disconnect[1]));
			} else {
				sb.append(dist(a, b));
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static long dist(int a, int b) {
		int diff = depth[a] - depth[b];
		if (diff < 0) {
			int temp = a;
			a = b;
			b = temp;
			diff = -diff;
		}
		long d = 0;
		for (int k = logn; k >= 0; k--) {
			if (1 << k > diff) continue;
			d += dist[k][a];
			a = parents[k][a];
			diff -= 1 << k;
		}
		if (a == b) return d;
		for (int k = logn; k >= 0; k--) {
			if (parents[k][a] == parents[k][b]) continue;
			d += dist[k][a];
			d += dist[k][b];
			a = parents[k][a];
			b = parents[k][b];
		}
		d += dist[0][a];
		d += dist[0][b];
		return d;
	}

	private static boolean isCut(int a, int p) {
		int diff = depth[a] - depth[p];
		if (a == p) return true;
		if (diff <= 0) return false;
		for (int k = logn; k >= 0; k--) {
			if (1 << k > diff) continue;
			a = parents[k][a];
			diff -= 1 << k;
		}
		return a == p;
	}

	private static int[] findMax(int u, int v) {
		int[] disconnect = new int[2];
		int diff = depth[u] - depth[v];
		if (diff < 0) {
			int temp = u;
			u = v;
			v = temp;
			diff = -diff;
		}
		int su = u;
		int sv = v;
		long max = 0;
		for (int k = logn; k >= 0; k--) {
			if (1 << k > diff) continue;
			if (dist[0][maxEdges[k][u]] > max) {
				max = dist[0][maxEdges[k][u]];
				disconnect[0] = maxEdges[k][u];
				disconnect[1] = su;
			}
			u = parents[k][u];
			diff -= 1 << k;
		}
		if (u == v) return disconnect;
		for (int k = logn; k >= 0; k--) {
			if (parents[k][u] == parents[k][v]) continue;
			if (dist[0][maxEdges[k][u]] > max) {
				max = dist[0][maxEdges[k][u]];
				disconnect[0] = maxEdges[k][u];
				disconnect[1] = su;
			}
			if (dist[0][maxEdges[k][v]] > max) {
				max = dist[0][maxEdges[k][v]];
				disconnect[0] = maxEdges[k][v];
				disconnect[1] = sv;
			}
			u = parents[k][u];
			v = parents[k][v];
		}
		if (dist[0][maxEdges[0][u]] > max) {
			max = dist[0][maxEdges[0][u]];
			disconnect[0] = maxEdges[0][u];
			disconnect[1] = su;
		}
		if (dist[0][maxEdges[0][v]] > max) {
			max = dist[0][maxEdges[0][v]];
			disconnect[0] = maxEdges[0][v];
			disconnect[1] = sv;
		}
		
		return disconnect;
	}

	private static void makeTree() {
		depth = new int[n];
		parents = new int[logn + 1][n];
		dist = new long[logn + 1][n];
		maxEdges = new int[logn + 1][n];
		Arrays.fill(parents[0], -1);
		parents[0][0] = 0;
		setTree(0);
		
		for (int k = 0; k < logn; k++) {
			for (int i = 0; i < n; i++) {
				parents[k + 1][i] = parents[k][parents[k][i]];
				dist[k + 1][i] = dist[k][i] + dist[k][parents[k][i]];
				int v1 = maxEdges[k][i];
				int v2 = maxEdges[k][parents[k][i]];
				if (dist[0][v1] > dist[0][v2]) maxEdges[k + 1][i] = v1;
				else maxEdges[k + 1][i] = v2;
			}
		}
	}

	private static void setTree(int p) {
		for (int[] e : adj[p]) {
			int c = e[0];
			int w = e[1];
			if (parents[0][c] >= 0) continue;
			depth[c] = depth[p] + 1;
			parents[0][c] = p;
			dist[0][c] = w;
			maxEdges[0][c] = c;
			setTree(c);
		}
	}
}