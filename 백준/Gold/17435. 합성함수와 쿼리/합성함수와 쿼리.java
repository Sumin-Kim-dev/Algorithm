import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int[][] f = new int[m][19];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			f[i][0] = Integer.parseInt(st.nextToken()) - 1;
		}
		for (int k = 1; k < 19; k++) {
			for (int i = 0; i < m; i++) {
				f[i][k] = f[f[i][k - 1]][k - 1];
			}
		}
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int k = 0;
			while (n > 0) {
				if (n % 2 == 1) x = f[x][k];
				n /= 2;
				k++;
			}
			sb.append(x + 1).append('\n');
		}
		System.out.println(sb);
	}

}