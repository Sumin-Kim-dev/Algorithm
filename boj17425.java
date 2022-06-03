package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj17425 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		long g[] = g(1000000);
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			bw.write("" + g[N - 1]);
			bw.newLine();
		}
		bw.close();
	}

	static long[] g(int N) {
		long g[] = new long[N];
		long f[] = new long[N];
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j += i) {
				f[j - 1] += i;
			}
			if (i > 1)
				g[i - 1] = g[i - 2] + f[i - 1];
		}
		return g;
	}
}