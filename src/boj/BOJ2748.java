package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class BOJ2748 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		BigInteger a = BigInteger.ONE;
		BigInteger b = BigInteger.ZERO;
		BigInteger c = BigInteger.ONE;

		if (n == 0)
			c = BigInteger.ZERO;
		for (int i = 1; i < n; i++) {
			a = b;
			b = c;
			c = a.add(b);
		}
		bw.write(c.toString());
		bw.close();
	}
}