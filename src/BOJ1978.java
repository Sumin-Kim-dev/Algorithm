import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1978 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int a;
		int count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a = Integer.parseInt(st.nextToken());
			if (prime(a))
				count++;
		}
		System.out.println(count);
	}

	static boolean prime(int a) {
		int i = 2;
		do {
			if (a == 1 || a % i == 0 && a > i)
				return false;
			i++;
		} while (i * i <= a);
		return true;
	}
}