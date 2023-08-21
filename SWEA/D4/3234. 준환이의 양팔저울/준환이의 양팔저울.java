import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int[] nums;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			nums = new int[n];
			count = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums);
			do {
				backtracking(0, 0, 0);
			} while (np());
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	private static boolean np() {
		int i = n - 1;
		while (i > 0 && nums[i - 1] >= nums[i]) i--;
		if (i == 0) return false;
		
		int j = n - 1;
		while (nums[i - 1] >= nums[j]) j--;
		int temp = nums[i - 1];
		nums[i - 1] = nums[j];
		nums[j] = temp;
		
		int k = n - 1;
		while (i < k) {
			temp = nums[i];
			nums[i] = nums[k];
			nums[k] = temp;
			i++;
			k--;
		}
		return true;
	}

	private static void backtracking(int cnt, int l, int r) {
		if (cnt == n) {
			count++;
			return;
		}
		backtracking(cnt + 1, l + nums[cnt], r);
		if (l >= r + nums[cnt]) {
			backtracking(cnt + 1, l, r + nums[cnt]);
		}
	}

}