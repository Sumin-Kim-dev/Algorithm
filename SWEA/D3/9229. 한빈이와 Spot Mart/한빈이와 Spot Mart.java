import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int m;
	static int[] a;
	
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			a = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			max = -1;
			combi(0, 0, 0);
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb);
	}
	
	private static void combi(int cnt, int start, int sum) {
		if (sum > m) return;
		if (cnt == 2) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = start; i < n; i++) {
			combi(cnt + 1, i + 1, sum + a[i]);
		}
	}
}