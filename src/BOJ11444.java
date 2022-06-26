

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11444 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	final static int p = 1000000007;

	public static void main(String[] args) throws IOException {

		long n = Long.parseLong(br.readLine());
		long FibonacciMatrix[][] = { { 1, 1 }, { 1, 0 } };
		long Fibonacci[][] = { { 1 }, { 0 } };
		bw.write(matMul(pow(FibonacciMatrix, n), Fibonacci)[1][0] + "");
		bw.close();
	}

	static long[][] matMul(long a[][], long b[][]) {
		long ab[][] = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++)
					ab[i][j] = (ab[i][j] + a[i][k] * b[k][j]) % p;
			}
		}
		return ab;
	}

	static long[][] pow(long matrix[][], long b) {
		int n = matrix.length;
		long[][] ans = new long[n][n];
		for (int i = 0; i < n; i++)
			ans[i][i] = 1;
		String binaryB = Long.toBinaryString(b);
		for (int i = 0; i < binaryB.length(); i++) {
			ans = matMul(ans, ans);
			if (binaryB.charAt(i) == '1')
				ans = matMul(ans, matrix);
		}
		return ans;
	}
}
