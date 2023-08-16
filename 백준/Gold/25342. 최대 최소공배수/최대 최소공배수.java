import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if (n == 4) {
				sb.append(12);
			} else if ((n & 1) != 0) {
				sb.append(1L * n * (n - 1) * (n - 2));
			} else if (n % 6 == 0){
				sb.append(1L * (n - 1) * (n - 2) * (n - 3));
			} else {
				sb.append(1L * n * (n - 1) * (n - 3));
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}