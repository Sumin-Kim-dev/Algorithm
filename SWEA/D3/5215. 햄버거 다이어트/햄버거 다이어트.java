import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int l;
	static int[] score;
	static int[] k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			score = new int[n];
			k = new int[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			int[] max = new int[l + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = l; j >= 0; j--) {
					if (j < k[i - 1]) continue;
					max[j] = Math.max(max[j], max[j - k[i - 1]] + score[i - 1]);
				}
			}
			sb.append("#" + t + " " + max[l] + "\n");
		}
		System.out.println(sb);
	}

}