import java.io.*;

public class Main {

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