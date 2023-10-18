import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int max;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static char[] dir = {' ', '↑', '↖', '←', '↙', '↓', '↘', '→', '↗'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4]; // 물고기 번호 : 상어 -1
		int[][] fish = new int[17][3]; // (r, c, d) : 먹혔으면 d = -1, 상어는 0번
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				fish[map[i][j]][0] = i;
				fish[map[i][j]][1] = j;
				fish[map[i][j]][2] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		max = 0;
		dfs(map, fish, 0, 0, 0);
		System.out.println(max);
	}

	private static void dfs(int[][] map, int[][] fish, int i, int j, int currSum) {
		int[][] copyMap = new int[4][4];
		int[][] copyFish  = new int[17][3];
		copy(map, copyMap);
		copy(fish, copyFish);
		currSum += copyMap[i][j];
		copyFish[0][0] = i;
		copyFish[0][1] = j;
		copyFish[0][2] = copyFish[copyMap[i][j]][2];
		copyFish[copyMap[i][j]][2] = -1;
		copyMap[i][j] = -1;
		max = Math.max(max, currSum);
		move(copyMap, copyFish);
		for (int k = 1; k < 4; k++) {
			int nr = i + k * dr[copyFish[0][2]];
			int nc = j + k * dc[copyFish[0][2]];
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) break;
			if (copyMap[nr][nc] == 0) continue;
			copyMap[i][j] = 0;
			dfs(copyMap, copyFish, nr, nc, currSum);
		}
	}

	private static void move(int[][] map, int[][] fish) {
		for (int k = 1; k <= 16; k++) {
			int r = fish[k][0];
			int c = fish[k][1];
			int d = fish[k][2];
			if (d == -1) continue; // 이미 먹힌 물고기는 스킵
			for (int dd = 0; dd < 8; dd++) {
				int nr = r + dr[(d + dd) % 8];
				int nc = c + dc[(d + dd) % 8];
				if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue; // 벽 넘어감
				if (map[nr][nc] == -1) continue; // 상어 있는 칸
				// 지도 swap
				int nf = map[nr][nc];
				map[nr][nc] = k;
				map[r][c] = nf;
				// 물고기 방향 update
				fish[k][2] = (d + dd) % 8;
				// 물고기 정보 swap
				if (nf == 0) {
					fish[k][0] = nr;
					fish[k][1] = nc;
				} else {
					int temp = fish[k][0];
					fish[k][0] = nr;
					fish[nf][0] = temp;
					temp = fish[k][1];
					fish[k][1] = nc;
					fish[nf][1] = temp;
				}
				break;
			}
		}
	}

	private static void copy(int[][] map, int[][] copy) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
}