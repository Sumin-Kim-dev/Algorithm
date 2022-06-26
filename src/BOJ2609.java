import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int gcd = gcd(a, b);
		int lcm = a * b / gcd;
		bw.write(gcd + "\n" + lcm);
		bw.close();
	}

	static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b;
		if (a >= b)
			return gcd(a % b, b);
		else
			return gcd(a, b % a);
	}
}
