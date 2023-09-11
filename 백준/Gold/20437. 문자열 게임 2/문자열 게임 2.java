import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			String str = br.readLine();
			int k = Integer.parseInt(br.readLine());
			sb.append(result(str, k)).append('\n');
		}
		System.out.println(sb);
	}

	private static String result(String str, int k) {
		int min = str.length() + 1;
		int max = -1;
		for (int i = 0; i < 26; i++) {
			char c = (char) ('a' + i);
			int s = 0;
			int e = 1;
			int count = 0;
			if (str.charAt(0) == c) count++;
			while (s < e) {
				if (e == str.length() && count < k) break;
				if (count == k) {
					min = Math.min(min, e - s);
					if (str.charAt(s) == str.charAt(e - 1)) max = Math.max(max, e - s);
				}
				if (count >= k) {
					if (str.charAt(s) == c) count--;
					s++;
				} else {
					if (str.charAt(e) == c) count++;
					if (e < str.length()) e++;
				}
			}
		}
		if (max == -1) return String.valueOf(-1);
		return String.format("%d %d", min, max);
	}

}