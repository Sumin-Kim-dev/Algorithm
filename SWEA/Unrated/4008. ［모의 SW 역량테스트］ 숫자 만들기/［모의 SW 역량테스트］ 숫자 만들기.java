import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int[] ops = new int[4];
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
			for (int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			nums = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			max = -100_000_001;
			min = 100_000_001;
			dfs(0, nums[0]);
			sb.append("#" + t + " " + (max - min) + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt, int curr) {
		if (cnt == n - 1) {
			max = Math.max(max, curr);
			min = Math.min(min, curr);
			return;
		}
		if (ops[0] > 0) {
			ops[0]--;
			dfs(cnt + 1, curr + nums[cnt + 1]);
			ops[0]++;
		}
		if (ops[1] > 0) {
			ops[1]--;
			dfs(cnt + 1, curr - nums[cnt + 1]);
			ops[1]++;
		}
		if (ops[2] > 0) {
			ops[2]--;
			dfs(cnt + 1, curr * nums[cnt + 1]);
			ops[2]++;
		}
		if (ops[3] > 0) {
			ops[3]--;
			dfs(cnt + 1, curr / nums[cnt + 1]);
			ops[3]++;
		}
	}
}