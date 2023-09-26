import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int N = 10;
	static int[][] map;
	static int max = 0;
	static int min = 17;
	static int[] cnt = new int[5];
	static int tot = 0;
	static int[][] last = new int[5][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max += map[i][j];
			}
		}
		backtracking(max, 5);
		System.out.println(min < 17 ? min : -1);
	}
	
	private static void backtracking(int remain, int maxSize) {
		if (tot >= min) return;
		if (remain == 0) {
			min = tot;
			return;
		}
		int addMax = 0;
		int addCount = min - tot;
		int ms = maxSize;
		while (addCount > 0 && ms > 0) {
			addMax += Math.min(addCount, 5 - cnt[ms - 1]) * ms * ms;
			addCount -= Math.min(addCount, 5 - cnt[ms - 1]);
			ms--;
		}
		if (remain > addMax) return;
		for (int k = maxSize; k > 0; k--) {
            if (cnt[k - 1] == 5) continue;
			remain -= k * k;
			int lastKr = last[k - 1][0];
			int lastKc = last[k - 1][1];
			for (int i = lastKr; i <= N - k; i++) {
				for (int j = 0; j <= N - k; j++) {
					if (cnt[k - 1] > 0 && i == lastKr && j < lastKc + k) continue;
					if (isAble(i, j, k)) {
						addPaper(i, j, k);
						tot++;
						cnt[k - 1]++;
						last[k - 1][0] = i;
						last[k - 1][1] = j;
						backtracking(remain, k);
						cnt[k - 1]--;
						tot--;
						removePaper(i, j, k);
					}
				}
			}
			last[k - 1][0] = lastKr;
			last[k - 1][1] = lastKc;
			remain += k * k;
		}
	}

	private static boolean isAble(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[r + i][c + j] == 0) return false;
			}
		}
		return true;
	}
	
	private static void addPaper(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[r + i][c + j] = 0;
			}
		}
	}
	
	private static void removePaper(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[r + i][c + j] = 1;
			}
		}
	}
}