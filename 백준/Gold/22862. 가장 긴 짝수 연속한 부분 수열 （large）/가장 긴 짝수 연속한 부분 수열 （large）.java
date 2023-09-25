import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] s = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st.nextToken()) & 1;
		}
		int start = 0;
		int end = 1;
		int sum = s[start];
		int max = end - start - sum;
		while (end < n) {
			if (sum <= k) {
				if (max < end - start - sum) max = end - start - sum;
				sum += s[end++];
				continue;
			} else {
				sum -= s[start++];
			}
		}
		if (max < end - start - sum) max = end - start - sum;
		System.out.println(max);
	}
}