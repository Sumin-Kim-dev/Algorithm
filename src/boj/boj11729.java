package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj11729 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		System.out.println((int) Math.pow(2, N) - 1);
		Hanoi(N, 1, 3);
		bw.flush();
		bw.close();
	}

	static void Hanoi(int N, int a, int b) throws IOException {
		int c = 6 - a - b;
		if (N == 0)
			return;
		else {
			Hanoi(N - 1, a, c);
			bw.write(a + " " + b);
			bw.newLine();
			Hanoi(N - 1, c, b);
		}
	}
}