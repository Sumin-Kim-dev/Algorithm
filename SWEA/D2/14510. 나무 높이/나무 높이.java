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
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] diff = new int[n];
			int max = 0;
			for (int i = 0; i < n; i++) {
				diff[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, diff[i]);
			}
			int tot = 0;
			int min1 = 0;
			for (int i = 0; i < n; i++) {
				diff[i] = max - diff[i];
				tot += diff[i];
				min1 += (diff[i] % 2);
			}
			min1 |= (tot % 2);
			int answer = 0;
			if (tot >= 3 * min1) answer = tot - tot / 3;
			else if (min1 > (tot - min1) >> 1) answer = (min1 << 1) - 1;
			else answer = (tot - min1) >> 1 << 1;
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

}