import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2775 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		int k, n;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(bf.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(bf.readLine());
			System.out.println(people(k, n));
		}
	}

	static int people(int k, int n) {
		int num;
		if (k == 0)
			num = n;
		else if (n == 0)
			return people(k - 1, n);
		else
			return people(k - 1, n) + people(k, n - 1);
		return num;
	}
}
