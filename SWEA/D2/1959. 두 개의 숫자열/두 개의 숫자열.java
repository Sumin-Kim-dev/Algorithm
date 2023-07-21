import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] a = input(new StringTokenizer(br.readLine()), n);
			int[] b = input(new StringTokenizer(br.readLine()), m);
			sb.append(String.format("#%d %d\n", i, answer(a, b, n, m)));
		}
        System.out.println(sb);
	}
	
	private static int[] input(StringTokenizer st, int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		return a;
	}
	
	private static int answer(int[] a, int[] b, int n, int m) {
		int max = Integer.MIN_VALUE;
		if (n >= m) {
			for (int k = 0; k <= n - m; k++) {
				int curr = 0;
				for (int i = 0; i < m; i++) {
					curr += a[i + k] * b[i];
				}
				if (curr > max) max = curr;
			}
		} else {
			for (int k = 0; k <= m - n; k++) {
				int curr = 0;
				for (int i = 0; i < n; i++) {
					curr += a[i] * b[i + k];
				}
				if (curr > max) max = curr;
			}
		}
		return max;
	}
}