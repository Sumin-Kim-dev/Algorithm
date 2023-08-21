import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		long[] nums = new long[n];
		long[] after = new long[n];
		long[] sum = new long[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i] = Long.parseLong(br.readLine());
			after[i] = nums[i];
			sum[i + 1] = sum[i] + nums[i];
		}
		
		long[][] change = new long[m][2];
		int index = 0;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				long c = Long.parseLong(st.nextToken());
				change[index][0] = b;
				change[index][1] = c - after[b];
				after[b] = c;
				index++;
				continue;
			}
			long diff = 0;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			for (int j = 0; j < index; j++) {
				if (change[j][0] >= b && change[j][0] <= c) {
					diff += change[j][1];
				}
			}
			sb.append(sum[c + 1] - sum[b] + diff).append('\n');
		}
		System.out.println(sb);
	}
	
}