import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] paper = new boolean[100][100];
		int answer = 0;
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!paper[x + i][y + j]) {
						paper[x + i][y + j] = true;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
	}

}