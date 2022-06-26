

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj9663 {

	static int N, ans = 0;
	static int chess[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		chess = new int[N];
		NQueen(0);
		bw.write(ans + "");
		bw.close();
	}

	static void NQueen(int num) {
		if (num == N) {
			ans++;
			return;
		}
		for (int x = 0; x < N; x++) {
			if (num == 0 || isAble(chess, num, x)) {
				chess[num] = x;
				NQueen(num + 1);
			}
		}
	}

	static boolean isAble(int chess[], int num, int x) {
		boolean isAble = true;
		for (int i = 0; i < num; i++) {
			isAble = isAble && !attack(chess[i], i, x, num);
		}
		return isAble;
	}

	static boolean attack(int cx, int cy, int dx, int dy) {
		return cx == dx || cy == dy || cx + cy == dx + dy || cx - cy == dx - dy;
	}
}