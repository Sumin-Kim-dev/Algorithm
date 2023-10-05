import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int k;
	static Cluster[] clusters;
	
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우 순
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			clusters = new Cluster[k];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				clusters[i] = new Cluster(r, c, count, d);
			}
			while (m-- > 0) {
				move();
			}
			int sum = 0;
			for (Cluster c : clusters) {
				sum += c.tot;
			}
			sb.append('#').append(t).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}

	private static void move() {
		int[][][] map = new int[n][n][2]; // (군집 번호, 기존 군집 최대치) 
		for (int i = 0; i < k; i++) {
			Cluster c = clusters[i];
			if (c.tot == 0) continue;
			c.r += dr[c.dir];
			c.c += dc[c.dir];
			if (c.r == 0 || c.r == n - 1 || c.c == 0 || c.c == n - 1) {
				c.tot /= 2;
				c.dir ^= 1;
			}
			if (map[c.r][c.c][1] == 0) { // 칸에 처음 도달
				map[c.r][c.c][0] = i;
				map[c.r][c.c][1] = c.tot;
				continue;
			}
			// 기존 칸에 있던 군집
			int k = map[c.r][c.c][0];
			if (map[c.r][c.c][1] < c.tot) { // 칸의 다른 군집을 흡수함
				clusters[k].dir = c.dir;
				map[c.r][c.c][1] = c.tot; // 최대치 갱신
			}
			clusters[k].tot += c.tot;
			c.tot = 0;
		}
	}

	static class Cluster {
		int r;
		int c;
		int tot;
		int dir;
		
		public Cluster(int r, int c, int tot, int dir) {
			this.r = r;
			this.c = c;
			this.tot = tot;
			this.dir = dir;
		}
	}
}