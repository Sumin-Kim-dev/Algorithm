import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			long answer = 1;
			List<Integer> prime = new ArrayList<>();
			for (int i = 1; i < n; i++) {
				int curr = arr[i];
				for (int p : prime) {
					if (arr[i] % p == 0) {
						curr /= p;
						curr *= (p - 1);
					}
				}
				if (curr == arr[i]) {
					prime.add(curr);
					curr--;
				}
				answer *= curr;
				answer %= MOD;
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}

}