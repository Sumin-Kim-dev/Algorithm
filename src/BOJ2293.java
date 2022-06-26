import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2293 {

	static int coin[], count[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		coin = new int[n];
		count = new int[n][k + 1];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			for (int j = 0; j < k + 1; j++)
				count[i][j] = -1; // 아직 구하지 않았을 때 -1
		}
		Arrays.sort(coin);
		bw.write(count(n - 1, k) + "");
		bw.close();
	}

	static int count(int m, int k) {
		if (k == 0)
			return 1;
		if (k < 0)
			return 0;
		if (count[m][k] != -1)
			return count[m][k];
		count[m][k] = 0;
		for (int i = 0; i <= m; i++) {
			count[m][k] += count(i, k - coin[i]);
		}
		return count[m][k];
	}
}
