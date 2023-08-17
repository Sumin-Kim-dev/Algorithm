import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			
			int[][][] p = new int[2][m + 1][2];
			p[0][0][0] = p[0][0][1] = 1;
			p[1][0][0] = p[1][0][1] = 10;
			for (int k = 0; k < 2; k++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 1; i <= m; i++) {
					int d = Integer.parseInt(st.nextToken());
					p[k][i][0] = p[k][i - 1][0] + dx[d];
					p[k][i][1] = p[k][i - 1][1] + dy[d];
				}
			}
			
			int[][] charge = new int[a][4];
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					charge[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int sum = 0;
			for (int k = 0; k <= m; k++) {
				int max = 0;
				for (int i = 0; i < a; i++) {
					int chargeA = isIn(charge[i], p[0][k]) ? 1 : 0;
					for (int j = 0; j < a; j++) {
						int chargeB = isIn(charge[j], p[1][k]) ? 1 : 0;
						int curr = charge[i][3] * chargeA + charge[j][3] * chargeB;
						if (i == j && chargeA + chargeB > 1) curr = charge[i][3];
						max = Math.max(max, curr);
					}
				}
				sum += max;
			}
			sb.append('#').append(t).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}

	private static boolean isIn(int[] charge, int[] p) {
		return Math.abs(charge[0] - p[0]) + Math.abs(charge[1] - p[1]) <= charge[2];
	}

}