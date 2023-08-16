import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int r;
	static int c;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				if (str.charAt(j) == '.') map[i][j] = true;
			}
		}
		
		int count = 0;
		for (int i = 0; i < r; i++) {
			if (isAble(i, 0)) count++;
		}
		System.out.println(count);
	}

	private static boolean isAble(int i, int j) {
		map[i][j] = false;
		if (j == c - 1) return true;
		for (int k = -1; k <= 1; k++) {
			if (i + k < 0 || i + k >= r) continue;
			if (!map[i + k][j + 1]) continue;
			if (isAble(i + k, j + 1)) return true;
		}
		return false;
	}

}