import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] a = new int[n + 2][2];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i][0] = Integer.parseInt(st.nextToken());
			a[i][1] = Integer.parseInt(st.nextToken());
		}
		a[0][1] = Integer.MIN_VALUE;
		a[n + 1][0] = Integer.MAX_VALUE;
		a[n + 1][1] = Integer.MAX_VALUE;
		Arrays.sort(a, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
				return -Integer.compare(o1[1], o2[1]);
			}
		});
		int[] dp = new int[n + 1];
		int[] lens = new int[n + 1];
		Arrays.fill(dp, n + 1);
		dp[0] = 0;
		int len = 0;
		for (int i = 1; i <= n; i++) {
			// 이분탐색 : a[dp[start]][1] < a[i][1] <= a[dp[end]][1]
			int start = 0;
			int end = len + 1;
			while (start + 1 < end) {
				int mid = (start + end) / 2;
				if (a[i][1] <= a[dp[mid]][1]) end = mid;
				else start = mid;
			}
			dp[end] = i;
			if (end > len) len++;
			lens[i] = end;
		}
		System.out.println(n - len);
		Stack<Integer> stack = new Stack<>();
		for (int i = n; i >= 1; i--) {
			if (lens[i] == len) {
				len--;
			} else {
				stack.push(a[i][0]);
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append('\n');
		}
		System.out.println(sb);
	}

}