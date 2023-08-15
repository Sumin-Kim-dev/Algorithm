import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] nums;
	static long[][] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		count = new long[n - 1][21];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		count[0][nums[0]] = 1;
		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j <= 20; j++) {
				if (j + nums[i] <= 20) count[i][j + nums[i]] += count[i - 1][j];
				if (j - nums[i] >= 0) count[i][j - nums[i]] += count[i - 1][j];
			}
		}
		System.out.println(count[n - 2][nums[n - 1]]);
	}
}