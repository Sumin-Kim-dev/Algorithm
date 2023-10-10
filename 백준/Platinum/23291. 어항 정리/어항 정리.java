import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] map = new int[10][n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			map[0][i] = Integer.parseInt(st.nextToken());
		}
		int t = 0;
		while (diff(map, n) > k) {
			t++;
			insert(map, n);
			fly(map, n); // 공중부양
			move(map, n); // 수 조절 후 flat
			fly2(map, n); // 공중부양2 : n/4 x 4로 만듬
			move(map, n); // 수 조절 후 flat
		}
		System.out.println(t);
	}

	private static int diff(int[][] map, int n) {
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, map[0][i]);
			max = Math.max(max, map[0][i]);
		}
		return max - min;
	}

	private static void insert(int[][] map, int n) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, map[0][i]);
		}
		for (int i = 0; i < n; i++) {
			if (map[0][i] == min) map[0][i]++;
		}
	}

	private static void fly(int[][] map, int n) {
		int start = 0;
		int end = 1;
		int h = 1;
		while (n >= (end - start + 1) * h) {
			for (int i = 0; i < h; i++) {
				for (int j = start; j < end; j++) {
					map[end - j][i + end] = map[i][j];
					map[i][j] = 0;
				}
			}
			int temp = h;
			h = end - start + 1;
			start = end;
			end = temp + end;
		}
	}
	
	private static void fly2(int[][] map, int n) {
		for (int i = 0; i < n / 4; i++) {
			map[1][3 * n / 4 + i] = map[0][n / 4 - 1 - i];
			map[2][3 * n / 4 + i] = map[0][n / 4 + i];
			map[3][3 * n / 4 + i] = map[0][3 * n / 4 - 1 - i];
		}
		for (int i = 0; i < 3 * n / 4; i++) {
			map[0][i] = 0;
		}
	}
	
	private static void move(int[][] map, int n) {
		int[][] newMap = new int[10][n];
		copy(map, newMap);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				if (newMap[i][j] == 0) continue;
				if (i > 0 && newMap[i - 1][j] > 0) {
					int diff = newMap[i][j] - newMap[i - 1][j];
					if (diff > 0) {
						map[i][j] -= diff / 5;
						map[i - 1][j] += diff / 5;
					} else {
						map[i][j] += (-diff) / 5;
						map[i - 1][j] -= (-diff) / 5;
					}
				}
				if (j > 0 && newMap[i][j - 1] > 0) {
					int diff = newMap[i][j] - newMap[i][j - 1];
					if (diff > 0) {
						map[i][j] -= diff / 5;
						map[i][j - 1] += diff / 5;
					} else {
						map[i][j] += (-diff) / 5;
						map[i][j - 1] -= (-diff) / 5;
					}
				}
			}
		}
		flat(map, n);
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void flat(int[][] map, int n) {
		int[][] newMap = new int[10][n];
		copy(map, newMap);
		int k = 0;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < 10; i++) {
				map[i][j] = 0;
				if (newMap[i][j] == 0) continue;
				map[0][k++] = newMap[i][j];
			}
		}
	}
}