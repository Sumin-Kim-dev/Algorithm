import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			int lis = 0;
			int[] minLast = new int[n];
			Arrays.fill(minLast, Integer.MAX_VALUE);
			for (int i : a) {
				int f = find(minLast, i);
				minLast[f] = i;
				if (f == lis) lis++;
			}
			sb.append('#').append(t).append(' ').append(lis).append('\n');
		}
		System.out.println(sb);
	}

	private static int find(int[] minLast, int i) {
		if (i <= minLast[0]) return 0;
		int start = 0;
		int end = minLast.length;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (minLast[mid] < i) start = mid;
			else end = mid;
		}
		return end;
	}

}