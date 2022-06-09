package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj17626 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		bw.write(squares(n) + "");
		bw.close();
	}

	static int squares(int n) {
		int squares[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			squares[i] = 4;
			for (int j = 1; j * j <= i; j++)
				if (squares[i] > squares[i - j * j])
					squares[i] = squares[i - j * j] + 1;
		}
		return squares[n];
	}
}
