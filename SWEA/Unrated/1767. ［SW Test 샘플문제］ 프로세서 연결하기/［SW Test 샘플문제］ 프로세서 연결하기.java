import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node {
		int cnt;
		int r;
		int c;
		Node next;
		
		public Node(int cnt, int r, int c, Node next) {
			this.cnt = cnt;
			this.r = r;
			this.c = c;
			this.next = next;
		}
	}
	
	static int n;
	static int[][] map;
	static Node core;
	static int nCore;
	
	static int maxConnected;
	static int minLength;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			core = null;
			nCore = 0;
			maxConnected = 0;
			minLength = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						core = new Node(nCore++, i, j ,core);
					}
				}
			}
			backtracking(core, 0, 0);
			sb.append('#').append(t).append(' ').append(minLength).append('\n');
		}
		System.out.println(sb);
	}

	private static void backtracking(Node core, int connected, int length) {
		if (connected > maxConnected || connected == maxConnected && length < minLength) {
			maxConnected = connected;
			minLength = length;
		}
		if (core == null) {
			return;
		}
		// 연결 안함
		backtracking(core.next, connected, length);
		// 연결
		int r = core.r;
		int c = core.c;
		int cnt = core.cnt;
		for (int d = 0; d < 4; d++) {
			if (canConnect(r, c, d)) {
				connect(r, c, cnt, d);
				backtracking(core.next, connected + 1, length + dist(r, c, d));
				disconnect(r, c, cnt, d);
			}
		}
	}

	private static int dist(int r, int c, int d) {
		if (d == 0) return r;
		if (d == 1) return n - 1 - c;
		if (d == 2) return n - 1 - r;
		if (d == 3) return c;
		return 0;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	private static boolean canConnect(int r, int c, int d) {
		r += dr[d];
		c += dc[d];
		while (isIn(r, c)) {
			if (map[r][c] > 0) return false;
			r += dr[d];
			c += dc[d];
		}
		return true;
	}
	
	private static void connect(int r, int c, int cnt, int d) {
		r += dr[d];
		c += dc[d];
		while (isIn(r, c)) {
			map[r][c] = cnt + 2;
			r += dr[d];
			c += dc[d];
		}
	}
	
	private static void disconnect(int r, int c, int cnt, int d) {
		r += dr[d];
		c += dc[d];
		while (isIn(r, c)) {
			if (map[r][c] == cnt + 2) {
				map[r][c] = 0;
			}
			r += dr[d];
			c += dc[d];
		}
	}
}