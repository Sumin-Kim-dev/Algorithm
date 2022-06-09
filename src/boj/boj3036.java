package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj3036 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int radius;
		for (int i = 1; i < N; i++) {
			radius = Integer.parseInt(st.nextToken());
			int gcd = gcd(first, radius);
			sb.append(first / gcd).append("/").append(radius / gcd).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b;
		if (a >= b)
			return gcd(a % b, b);
		else
			return gcd(a, b % a);
	}
}