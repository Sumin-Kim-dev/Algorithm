import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] combi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		setCombi();
		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sb.append(combi[m][n]).append('\n');
		}
		System.out.println(sb);
	}

	private static void setCombi() {
		combi = new int[30][30];
		combi[0][0] = 1;
		for (int i = 1; i < 30; i++) {
			combi[i][0] = 1;
			for (int j = 1; j < 30; j++) {
				combi[i][j] = combi[i - 1][j - 1] + combi[i - 1][j];
			}
		}
	}

}