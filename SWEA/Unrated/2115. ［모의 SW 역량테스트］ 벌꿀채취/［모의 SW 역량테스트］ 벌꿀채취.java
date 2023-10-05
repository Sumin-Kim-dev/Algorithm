import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[][] map;
	static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			cost = new int[n][n - m + 1];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			setTotCost(m, c);
			sb.append('#').append(t).append(' ').append(backtracking(m)).append('\n');
		}
		System.out.println(sb);
	}

	private static int backtracking(int m) {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				for (int k = j + m; k < n - m + 1; k++) {
					max = Math.max(max, cost[i][j] + cost[i][k]);
				}
				for (int k = i + 1; k < n; k++) {
					for (int l = 0; l < n - m + 1; l++) {
						max = Math.max(max, cost[i][j] + cost[k][l]);
					}
				}
			}
		}
		return max;
	}

	private static void setTotCost(int m, int c) {
		int[][] tot = new int[n][n - m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j < m) {
					tot[i][0] += map[i][j];
					cost[i][0] += map[i][j] * map[i][j];
				} else {
					tot[i][j - m + 1] = tot[i][j - m] + map[i][j] - map[i][j - m];
					cost[i][j - m + 1] = cost[i][j - m] + map[i][j] * map[i][j] - map[i][j - m] * map[i][j - m];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				if (tot[i][j] <= c)
					continue;
				cost[i][j] = 0;
				for (int flag = 0; flag < (1 << m); flag++) {
					int ctot = 0;
					int ccost = 0;
					for (int k = 0; k < m; k++) {
						if ((flag & (1 << k)) == 0)
							continue;
						ctot += map[i][j + k];
						if (ctot > c) {
							ccost = 0;
							break;
						}
						ccost += map[i][j + k] * map[i][j + k];
					}
					cost[i][j] = Math.max(cost[i][j], ccost);
				}
			}
		}
	}
}