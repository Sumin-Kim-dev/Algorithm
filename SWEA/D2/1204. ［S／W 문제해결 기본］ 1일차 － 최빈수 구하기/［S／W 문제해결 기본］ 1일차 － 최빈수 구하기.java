import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= t; i++) {
			int no = Integer.parseInt(br.readLine());
			int[] scores = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 1000; j++) {
				int curr = Integer.parseInt(st.nextToken());
				scores[curr]++;
			}
			int max = 0;
			int answer = -1;
			for (int k = 0; k <= 100; k++) {
				if (scores[k] >= max) {
					max = scores[k];
					answer = k;
				}
			}
			sb.append(String.format("#%d %d\n", no, answer));
		}
		System.out.println(sb);
	}
}
