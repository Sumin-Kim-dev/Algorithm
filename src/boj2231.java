

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2231 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int i = 1;
		for (i = 1; i < N; i++) {
			if (d(i) == N) {
				bw.write("" + i);
				break;
			}
		}
		if (i == N)
			bw.write('0');
		bw.close();
	}

	static int d(int i) {
		int d = i;
		while (i != 0) {
			d += i % 10;
			i /= 10;
		}
		return d;
	}
}
