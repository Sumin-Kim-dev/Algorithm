import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int d;
	static int w;
	static int k;
	static int[] map;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[d];
			min = k;
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i] <<= 1;
					map[i] |= Integer.parseInt(st.nextToken());
				}
			}
			backtracking(0, 0);
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static void backtracking(int cnt, int start) {
		if (isPass()) {
			min = cnt;
			return;
		}
		if (cnt >= min) return;
		for (int i = start; i < d; i++) {
			int before = map[i];
			map[i] = 0;
			backtracking(cnt + 1, i + 1);
			map[i] = (1 << w) - 1;
			backtracking(cnt + 1, i + 1);
			map[i] = before;
		}
	}

	private static boolean isPass() {
		int[] sum = new int[w];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < k; j++) {
				sum[i] += (map[j] >> i) & 1;
			}
			for (int j = k; j < d; j++) {
				if (sum[i] == 0 || sum[i] == k) break;
				sum[i] += (map[j] >> i) & 1;
				sum[i] -= (map[j - k] >> i) & 1;
			}
			if (sum[i] != 0 && sum[i] != k) return false;
		}
		return true;
	}

}