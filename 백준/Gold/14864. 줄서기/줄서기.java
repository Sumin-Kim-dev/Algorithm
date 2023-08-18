import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] info = new int[n + 1][2];
		int[][] edge = new int[m][2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			info[x][0]++;
			info[y][1]++;
			edge[i][0] = x;
			edge[i][1] = y;
		}
		int[] result = new int[n + 1];
		int[] order = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			result[i] = info[i][0] - info[i][1] + i;
			if (order[result[i]] > 0) {
				System.out.println(-1);
				return;
			}
			order[result[i]] = i;
		}
		for (int i = 0; i < m; i++) {
			if (result[edge[i][0]] <= result[edge[i][1]]) {
				System.out.println(-1);
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(result[i] + " ");
		}
		System.out.println(sb);
	}

}