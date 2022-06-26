import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2004 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int twos = 0, fives = 0;
		twos = twos(n) - twos(m) - twos(n - m);
		fives = fives(n) - fives(m) - fives(n - m);
		bw.write((twos >= fives ? fives : twos) + "");
		bw.close();
	}

	static int twos(int n) {
		int twos = 0;
		while (n >= 2) {
			n /= 2;
			twos += n;
		}
		return twos;
	}

	static int fives(int n) {
		int fives = 0;
		while (n >= 5) {
			n /= 5;
			fives += n;
		}
		return fives;
	}
}
