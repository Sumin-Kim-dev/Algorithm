import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] nums = new int[2][];
	static long[][] sums = new long[2][4000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums[0] = new int[n / 2];
		nums[1] = new int[n - n / 2];
		for (int i = 0; i < n; i++) {
			if (i < n / 2) nums[0][i] = Integer.parseInt(st.nextToken());
			else nums[1][i - n / 2] = Integer.parseInt(st.nextToken());
		}
		subset(0, 0, 0);
		subset(1, 0, 0);
		long answer = 0;
		for (int i = Math.max(s, 0); i <= 4000000 + Math.min(s, 0); i++) {
			answer += sums[0][i] * sums[1][s + 4000000 - i];
		}
		if (s == 0) answer--;
		System.out.println(answer);
	}

	private static void subset(int index, int cnt, int sum) {
		if (cnt == nums[index].length) {
			sums[index][sum + 2000000]++;
			return;
		}
		subset(index, cnt + 1, sum);
		subset(index, cnt + 1, sum + nums[index][cnt]);
	}

}