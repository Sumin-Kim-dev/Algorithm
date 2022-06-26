

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11720 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(bf.readLine());
		String s = bf.readLine();
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += s.charAt(i) - '0';
		}
		bw.write("" + sum);
		bw.flush();
		bw.close();
	}
}