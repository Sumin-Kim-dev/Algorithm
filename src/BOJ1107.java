import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1107 {

	static boolean button[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		button = new boolean[10];
		if (m != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens())
				button[Integer.parseInt(st.nextToken())] = true;
		}
		bw.write(count(n) + "");
		bw.close();
	}

	static int count(int n) {
		int min = n > 100 ? n - 100 : 100 - n;
		for (int i = 0; i < min; i++) {
			if (n >= i && able(n - i)) {
				if (digit(n - i) + i < min)
					min = digit(n - i) + i;
				break;
			}
			if (able(n + i)) {
				if (digit(n + i) + i < min)
					min = digit(n + i) + i;
				break;
			}
		}
		return min;
	}

	static int digit(int n) {
		if (n == 0)
			return 1;
		int digit = 0;
		while (n > 0) {
			n /= 10;
			digit++;
		}
		return digit;
	}

	static boolean able(int n) {
		if (n == 0)
			return !button[0];
		boolean able = true;
		while (n > 0) {
			able = able && !button[n % 10];
			n /= 10;
		}
		return able;
	}
}
