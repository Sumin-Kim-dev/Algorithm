import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 10_000_000;
	
	static int n;
	static int[][] minDist;
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		minDist = new int[n][n];
		table = new int[n][n];
		int m = Integer.parseInt(st.nextToken());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			minDist[s][e] = minDist[e][s] = w;
			table[s][e] = e;
			table[e][s] = s;
		}
		floydWarshall();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) sb.append('-');
				else sb.append(table[i][j] + 1);
				sb.append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void floydWarshall() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j || minDist[i][j] > 0) continue;
				minDist[i][j] = INF;
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (minDist[i][j] > minDist[i][k] + minDist[k][j]) {
						minDist[i][j] = minDist[i][k] + minDist[k][j];
						table[i][j] = table[i][k];
					}
				}
			}
		}
	}
}