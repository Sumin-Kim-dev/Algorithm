import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int h;
	static int w;
	static char[][] map;

	static int[] cmd;
	static char[] dir = {'^', '>', 'v', '<'};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			int sr = 0;
			int sc = 0;
			int sd = 0;
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						for (int d = 0; d < 4; d++) {
							if (map[i][j] == dir[d]) {
								sd = d;
								break;
							}
						}
						map[i][j] = '.';
						sr = i;
						sc = j;
					}
				}
			}
			int n = Integer.parseInt(br.readLine());
			cmd = new int[n];
			String str = br.readLine();
			for (int i = 0; i < n; i++) {
				char c = str.charAt(i);
				switch (c) {
				case 'U':
					cmd[i] = 0;
					break;
				case 'R':
					cmd[i] = 1;
					break;
				case 'D':
					cmd[i] = 2;
					break;
				case 'L':
					cmd[i] = 3;
					break;
				default:
					cmd[i] = 4;
					break;
				}
			}
			sb.append('#').append(t).append(' ').append(result(sr, sc, sd));
		}
		System.out.println(sb);
	}

	private static String result(int sr, int sc, int sd) {
		int cr = sr;
		int cc = sc;
		int cd = sd;
		for (int d : cmd) {
			if (d == 4) {
				int nr = cr;
				int nc = cc;
				while (isIn(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] == '-')) {
					nr += dr[cd];
					nc += dc[cd];
				}
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc] == '*') {
					map[nr][nc] = '.';
					continue;
				}
				continue;
			}
			cd = d;
			int nr = cr + dr[cd];
			int nc = cc + dc[cd];
			if (!isIn(nr, nc)) continue;
			if (map[nr][nc] == '.') {
				cr = nr;
				cc = nc;
			}
		}
		map[cr][cc] = dir[cd];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < h; i++) {
			sb.append(String.valueOf(map[i])).append('\n');
		}
		return sb.toString();
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

}