package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2448 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		bw.write(star(N));
		bw.close();
	}

	static String star(int N) {
		StringBuilder sb = new StringBuilder();
		if (N == 3) {
			sb.append("  *  \n").append(" * * \n").append("*****");
			return sb.toString();
		}
		String before = star(N / 2);
		String[] beforeRow = before.split("\n");
		for (int i = 0; i < beforeRow.length; i++) {
			sb.append(blank(N / 2)).append(beforeRow[i]).append(blank(N / 2)).append('\n');
		}
		for (int i = 0; i < beforeRow.length; i++) {
			sb.append(beforeRow[i]).append(' ').append(beforeRow[i]).append('\n');
		}
		return sb.toString();
	}

	static String blank(int N) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append(' ');
		return sb.toString();
	}
}
