package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2740 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int a[][] = matrix();
		int b[][] = matrix();
		int c[][] = matMul(a, b);
		print(c);
	}

	static int[][] matrix() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int a[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				a[i][j] = Integer.parseInt(st.nextToken());
		}
		return a;
	}

	static int[][] matMul(int a[][], int b[][]) {
		int ab[][] = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++)
					ab[i][j] += a[i][k] * b[k][j];
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
}
