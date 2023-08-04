import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] results;
	static int[] order;
	static int max;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		results = new int[n][9];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[9];
		visited = new boolean[9];
		order[3] = 1;
		visited[0] = true;
		max = 0;
		perm(0);
		System.out.println(max);
	}
	
	private static void perm(int cnt) {
		if (cnt == 9) {
			max = Math.max(max, score());
			return;
		}
		for (int i = 2; i <= 9; i++) {
			if (visited[i - 1]) continue;
			visited[i - 1] = true;
			order[cnt] = i;
			if (cnt == 2) perm(4);
			else perm(cnt + 1);
			visited[i - 1] = false;
		}
	}
	
	private static int score() {
		int curr = 0;
		int score = 0;
		for (int inning = 0; inning < n; inning++) {
			int currScore = 0;
			int out = 0;
			boolean[] base = new boolean[3];
			while (out < 3) {
				int result = results[inning][order[curr] - 1];
				curr = (curr + 1) % 9;
				switch (result) {
				case 0:
					out++;
					break;
				case 1:
					if (base[2]) score++;
					base[2] = base[1];
					base[1] = base[0];
					base[0] = true;
					break;
				case 2:
					if (base[2]) score++;
					if (base[1]) score++;
					base[2] = base[0];
					base[1] = true;
					base[0] = false;
					break;
				case 3:
					if (base[2]) score++;
					if (base[1]) score++;
					if (base[0]) score++;
					base[2] = true;
					base[1] = base[0] = false;
					break;
				case 4:
					score++;
					if (base[2]) score++;
					if (base[1]) score++;
					if (base[0]) score++;
					base[2] = base[1] = base[0] = false;
					break;
				}
			}
			score += currScore;
		}
		return score;
	}
	
}