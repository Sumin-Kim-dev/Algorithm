import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2156 {
	static int wine[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		wine = new int[n];
		for (int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		bw.write(total_wine(n) + "");
		bw.close();
	}

	static int total_wine(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return wine[0];
		if (n == 2)
			return wine[0] + wine[1];
		int wine_nMinus2 = 0;
		int wine_nMinus1 = wine[0];
		int wine_n = wine[0] + wine[1], temp;
		for (int i = 3; i <= n; i++) {
			temp = wine_n;
			wine_n = max(wine_n, wine_nMinus1 + wine[i - 1]);
			wine_n = max(wine_n, wine_nMinus2 + wine[i - 2] + wine[i - 1]);
			wine_nMinus2 = wine_nMinus1;
			wine_nMinus1 = temp;
		}
		return wine_n;
	}

	static int max(int x, int y) {
		return x > y ? x : y;
	}
}
