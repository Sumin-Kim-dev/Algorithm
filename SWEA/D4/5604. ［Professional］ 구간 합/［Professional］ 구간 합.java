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
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			sb.append('#').append(t).append(' ').append(answer(a, b)).append('\n');
		}
		System.out.println(sb);
	}

	private static long answer(long a, long b) {
		return answer(b) - answer(a - 1);
	}

	private static long answer(long a) {
		if (a <= 9) return a * (a + 1) / 2;
		long first = a;
		long digit = 1;
		int count = 0;
		while (first >= 10) {
			first /= 10;
			digit *= 10;
			count++;
		}
		return first * (a - first * digit + 1) + answer(a - first * digit)
			+ first * (first - 1) / 2 * digit + first * 45 * count * digit / 10;
	}

}