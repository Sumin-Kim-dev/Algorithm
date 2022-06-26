import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11653 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		factorization(N);
	}

	static void factorization(int N) {
		if (N == 1)
			return;
		else {
			int factor;
			for (factor = 2; factor <= N; factor++) {
				if (N % factor == 0) {
					System.out.println(factor);
					break;
				}
			}
			factorization(N / factor);
		}
	}
}
