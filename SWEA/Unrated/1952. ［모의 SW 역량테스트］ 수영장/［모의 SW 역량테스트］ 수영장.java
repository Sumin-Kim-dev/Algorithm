import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int d;
	static int m;
	static int m3;
	static int y;
	static int[] schedule;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			m3 = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			schedule = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}
			min = 400 * d;
			backtracking(0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}

	private static void backtracking(int month, int curr) {
		if (month >= 12) {
			min = Math.min(min, curr);
			return;
		}
		if (curr > min) return;
		if (d * schedule[month] < m) {
			backtracking(month + 1, curr + d * schedule[month]);
		} else {
			backtracking(month + 1, curr + m);
		}
		backtracking(month + 3, curr + m3);
		backtracking(12, y);
	}

}