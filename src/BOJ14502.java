import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {

	final static int[] xAdd = { -1, 1, 0, 0 }, yAdd = { 0, 0, -1, 1 };
	static int[][] lab;
	static int n, m, blankSize, maxSafeSize = 0;
	static boolean[][] check;
	static ArrayList<int[]> blank = new ArrayList<>();
	static int[] seq = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 0)
					blank.add(new int[] { i, j });
			}
		}
		blankSize = blank.size();
		backTracking(0);
		bw.write(maxSafeSize + "");
		bw.close();
	}

	static void backTracking(int depth) {
		if (depth == 3) {
			int safeSize = safeSize(lab);
			if (maxSafeSize < safeSize)
				maxSafeSize = safeSize;
			return;
		}
		// 백트래킹
		for (int i = 0; i < blankSize; i++) {
			int[] wall = blank.get(i);
			if (depth != 0 && seq[depth - 1] >= i)
				continue;
			seq[depth] = i;
			lab[wall[0]][wall[1]] = 1;
			backTracking(depth + 1);
			lab[wall[0]][wall[1]] = 0;
		}
	}

	static int safeSize(int[][] currLab) {
		int safeSize = blankSize - 3;
		boolean[][] check = new boolean[n][m];
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int[] loc = { i, j };
				// 바이러스 없는 칸은 패스
				if (currLab[i][j] != 2)
					continue;
				// 이미 바이러스 있다고 체크한 칸도 패스
				if (check[i][j])
					continue;
				// 처음 방문하는 바이러스 있는 칸
				queue.add(loc);
				check[i][j] = true;
				while (!queue.isEmpty()) {
					int[] curr = queue.poll();
					for (int k = 0; k < 4; k++) {
						int x = curr[0] + xAdd[k];
						int y = curr[1] + yAdd[k];
						// 범위 밖이면 스킵
						if (x < 0 || x >= n || y < 0 || y >= m)
							continue;
						// 이미 체크한 곳이면 스킵
						if (check[x][y])
							continue;
						// 바이러스가 옮겨갈 수 있으면
						if (currLab[x][y] == 0) {
							check[x][y] = true;
							queue.add(new int[] { x, y });
							safeSize--;
						}
					}
				}
			}
		}
		return safeSize;
	}
}
