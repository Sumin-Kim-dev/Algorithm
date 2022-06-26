import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ17427 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		bw.write("" + g(N));
		bw.flush();
		bw.close();
	}

	static long g(int N) {
		long g = 0;
		for (int i = 1; i <= N; i++)
			g += N / i * i;
		return g;
	}
}
