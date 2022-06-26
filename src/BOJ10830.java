

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10830 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	final static int R = 1000;

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		int a[][] = matrix(n);
		int pow[][] = pow(a, b);
		print(pow);
	}

	static int[][] matrix(int n) throws IOException {
		StringTokenizer st;
		int a[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				a[i][j] = Integer.parseInt(st.nextToken());
		}
		return a;
	}

	static int[][] matMul(int a[][], int b[][]) {
		int ab[][] = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++)
					ab[i][j] = (ab[i][j] + a[i][k] * b[k][j]) % R;
			}
		}
		return ab;
	}

	static void print(int matrix[][]) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				sb.append(matrix[i][j]).append(' ');
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int[][] pow(int matrix[][], long b) {
		int n = matrix.length;
		int[][] ans = new int[n][n];
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
