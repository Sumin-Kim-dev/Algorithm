

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11726 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		bw.write(Fibonacci(n) + "");
		bw.close();
	}

	static int Fibonacci(int n) {
		int Fibonacci_nMinus1 = 1, Fibonacci_n = 1, temp;
		for (int i = 2; i <= n; i++) {
			temp = Fibonacci_n;
			Fibonacci_n = (Fibonacci_nMinus1 + Fibonacci_n) % 10007;
			Fibonacci_nMinus1 = temp;
		}
		return Fibonacci_n;
	}
}
