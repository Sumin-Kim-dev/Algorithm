import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int plus, minus, mul, divide;
	static int[] nums;
	static int max;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			plus = Integer.parseInt(st.nextToken());
			minus = Integer.parseInt(st.nextToken());
			mul = Integer.parseInt(st.nextToken());
			divide = Integer.parseInt(st.nextToken());
			
			nums = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			max = -100_000_001;
			min = 100_000_001;
			dfs(1, nums[0], plus, minus, mul, divide);
			sb.append("#" + t + " " + (max - min) + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt, int curr, int plus, int minus, int mul, int divide) {
		if (cnt == n) {
			max = Math.max(max, curr);
			min = Math.min(min, curr);
			return;
		}
		if (plus > 0) {
			dfs(cnt + 1, curr + nums[cnt], plus - 1, minus, mul, divide);
		}
		if (minus > 0) {
			dfs(cnt + 1, curr - nums[cnt], plus, minus - 1, mul, divide);
		}
		if (mul > 0) {
			dfs(cnt + 1, curr * nums[cnt], plus, minus, mul - 1, divide);
		}
		if (divide > 0) {
			dfs(cnt + 1, curr / nums[cnt], plus, minus, mul, divide - 1);
		}
	}
}