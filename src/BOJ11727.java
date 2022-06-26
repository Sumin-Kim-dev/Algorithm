

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11727 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		bw.write(answer(n) + "");
		bw.close();
	}

	static int answer(int n) {
		int answer_nMinus1 = 1, answer_n = 1, temp;
		for (int i = 2; i <= n; i++) {
			temp = answer_n;
			answer_n = (2 * answer_nMinus1 + answer_n) % 10007;
			answer_nMinus1 = temp;
		}
		return answer_n;
	}
}
