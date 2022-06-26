import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1074 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		bw.write(z(N, r, c) + "");
		bw.close();
	}

	static int z(int N, int r, int c) {
		if (N == 1)
			return r * 2 + c;
		int length = 1 << (N - 1);
		return z(N - 1, r % length, c % length) + length * length * ((r / length) * 2 + (c / length));
	}
}
