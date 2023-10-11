import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			long answer = -2;
			int mod2 = 0;
			int max = 0;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				if (answer == -1) continue;
				if (answer == -2) {
					answer = 0;
					mod2 = x & 1;
				}
				if (mod2 != (x & 1)) {
					answer = -1;
					continue;
				}
				if (x > max) max = x;
			}
			if (answer != -1) answer = min(max);
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	private static long min(long x) {
		if (x == 0) return 0;
		long start = 0;
		long end = 64000;
		while (start + 1 < end) {
			long mid = (start + end) / 2;
			if (2L * x <= mid * (mid + 1)) end = mid;
			else start = mid;
		}
		if ((x - (end * (end + 1) / 2) & 1) == 0) return end;
		return ((end + 1) >> 1 << 1) + 1;
	}

}