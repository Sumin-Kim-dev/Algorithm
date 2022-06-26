import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ15829 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int L = Integer.parseInt(br.readLine());
		String str = br.readLine();
		long rPow = 1;
		long hash = 0;
		long r = 31, M = 1234567891;
		for (int i = 0; i < L; i++) {
			hash = (hash + ((long) (str.charAt(i) - 'a') + 1) * rPow) % M;
			rPow = (rPow * r) % M;
		}

		bw.write("" + hash);
		bw.close();
	}
}
