import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[][] magnets = new int[4][8];
		for (int t = 1; t <= tc; t++) {
			int k = Integer.parseInt(br.readLine());
			StringTokenizer st;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] rotates = new int[k][2];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				rotates[i][0] = Integer.parseInt(st.nextToken()) - 1;
				rotates[i][1] = Integer.parseInt(st.nextToken());
			}
			sb.append('#').append(t).append(' ').append(score(magnets, rotates)).append('\n');
		}
		System.out.println(sb);
	}

	private static int score(int[][] magnets, int[][] rotates) {
		int score = 0;
		int[] up = new int[4];
		boolean[] isDiff = new boolean[3];
		for (int[] rotate : rotates) {
			for (int i = 0; i < 3; i++) {
				isDiff[i] = magnets[i][(up[i] + 2) % 8] != magnets[i + 1][(up[i + 1] + 6) % 8];
			}
			int start = rotate[0];
			int next = start;
			int d = rotate[1];
			up[start] = (up[start] - d + 8) % 8;
			while (next < 3 && isDiff[next]) {
				next++;
				d *= -1;
				up[next] = (up[next] - d + 8) % 8;
			}
			next = start;
			d = rotate[1];
			while (next > 0 && isDiff[next - 1]) {
				next--;
				d *= -1;
				up[next] = (up[next] - d + 8) % 8;
			}
		}
		for (int i = 3; i >= 0; i--) {
			score *= 2;
			score += magnets[i][up[i]];
		}
		return score;
	}

}