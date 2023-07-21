import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			boolean[][] friends = new boolean[n][n];
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				friends[i][j] = friends[j][i] = true;
			}
			sb.append(clusters(n, friends)).append("\n");
		}
		System.out.println(sb);
	}

	private static int clusters(int n, boolean[][] friends) {
		boolean[] check = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (check[i]) continue;
			count++;
			dfs(i, n, check, friends);
		}
		return count;
	}

	private static void dfs(int i, int n, boolean[] check, boolean[][] friends) {
		for (int k = 0; k < n; k++) {
			if (!friends[i][k]) continue;
			if (check[k]) continue;
			check[k] = true;
			dfs(k, n, check, friends);
		}
	}

}
