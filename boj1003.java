package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1003 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		int n;
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			printFibonacci(n);
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void printFibonacci(int n) {
		int zeros_nMinus1 = -1, zeros_n = 1, temp;
		for (int i = 1; i <= n; i++) {
			temp = zeros_n;
			zeros_n = zeros_nMinus1 + zeros_n;
			zeros_nMinus1 = temp;
		}
		sb.append(zeros_n);
		sb.append(" ");
		sb.append(zeros_nMinus1 + zeros_n);
		sb.append("\n");
	}
}
