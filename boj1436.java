package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1436 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int i = 1;
		int n = 666;
		while (i < N) {
			n++;
			if (doom(n))
				i++;
		}
		bw.write("" + n);
		bw.close();
	}

	static boolean doom(int n) {
		while (n >= 100) {
			if ((n - 666) % 1000 == 0)
				return true;
			n /= 10;
		}
		return false;
	}
}
