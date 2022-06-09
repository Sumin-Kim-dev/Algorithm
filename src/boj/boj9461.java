package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj9461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int n;
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			sb.append(p(n) + "\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static long p(int n) {
		if (n <= 3)
			return 1;
		if (n <= 5)
			return 2;
		long p[] = new long[5], temp;
		p[0] = p[1] = p[2] = 1;
		p[3] = p[4] = 2;
		for (int i = 6; i <= n; i++) {
			temp = p[4];
			p[4] = p[4] + p[0];
			p[0] = p[1];
			p[1] = p[2];
			p[2] = p[3];
			p[3] = temp;
		}
		return p[4];
	}
}