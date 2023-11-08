import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n = 10;
	static int offset = 21;
	static int[] score = { 10, 13, 16, 19, 20, 22, 24, 30, 28, 27, 26, 25, 30, 35 };
	static int[] locs;
	static int[] nums;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		locs = new int[4];
		nums = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		backtracking(0, 0);
		System.out.println(max);
	}

	private static void backtracking(int depth, int curr) {
		if (depth == n) {
			max = Math.max(max, curr);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int before = locs[i];
			if (before == -1) continue;
			int after = move(before, nums[depth]);
			boolean isAble = true;
			for (int j = 0; j < 4; j++) {
				if (i == j) continue;
				if (after == locs[j] && after != -1) {
					isAble = false;
					break;
				}
			}
			if (!isAble) continue;
			locs[i] = after;
			backtracking(depth + 1, curr + score(locs[i]));
			locs[i] = before;
		}
	}
	
	private static int score(int loc) {
		if (loc < 0 || loc > 35) return 0;
		if (loc <= 20) return 2 * loc;
		return score[loc - offset];
	}

	private static int move(int before, int dice) {
		// 바깥 고리에서 돌고 있는 경우
		if (before >= 0 && before <= 20) {
			int after = before + dice;
			if (after > 20)
				return -1; // 도착점
			if (after != 5 && after != 10 && after != 15)
				return after; // 파란칸 X
			if (after == 5)
				return 21; // 10 써 있는 파란 칸
			if (after == 10)
				return 25; // 20 써 있는 파란 칸
			if (after == 15)
				return 28; // 30 써 있는 파란 칸
		}
		// 10 써 있는 파란 칸에서 시작한 경우
		if (before >= 21 && before < 25) {
			int after = before + dice;
			if (after >= 25)
				after += 7;
			if (after == 35) after = 20;
			if (after > 35) after = -1;
			return after;
		}
		// 20 써 있는 파란 칸에서 시작한 경우
		if (before >= 25 && before < 28) {
			int after = before + dice;
			if (after >= 28)
				after += 4;
			if (after == 35) after = 20;
			if (after > 35) after = -1;
			return after;
		}
		// 30 써 있는 파란 칸에서 시작한 경우
		if (before >= 28) {
			int after = before + dice;
			if (after == 35) after = 20;
			if (after > 35) after = -1;
			return after;
		}
		// 이미 도착점인 경우
		return -1;
	}
}