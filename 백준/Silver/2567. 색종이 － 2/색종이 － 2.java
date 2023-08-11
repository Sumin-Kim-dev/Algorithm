import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] paper = new boolean[102][102];
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) + 1;
			int y = Integer.parseInt(st.nextToken()) + 1;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!paper[x + i][y + j]) {
						paper[x + i][y + j] = true;
					}
				}
			}
		}
		int answer = 0;
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (!paper[i][j]) continue;
				if (!paper[i - 1][j]) answer++;
				if (!paper[i + 1][j]) answer++;
				if (!paper[i][j - 1]) answer++;
				if (!paper[i][j + 1]) answer++;
			}
		}
		System.out.println(answer);
	}

}