import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580 {

	static int sudoku[][];
	static ArrayList<Integer> blank = new ArrayList<>(); // 빈 칸의 인덱스 저장
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if (sudoku[i][j] == 0)
					blank.add(i * 9 + j);
			}
		}
		solve(0);
	}

	static void solve(int depth) throws IOException {
		if (depth == blank.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]);
					sb.append(" ");
				}
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.close();
			System.exit(0);
		}
		for (int i = 1; i <= 9; i++) {
			if (isAble(blank.get(depth) / 9, blank.get(depth) % 9, i)) {
				sudoku[blank.get(depth) / 9][blank.get(depth) % 9] = i;
				solve(depth + 1);
			}
		}
		sudoku[blank.get(depth) / 9][blank.get(depth) % 9] = 0;
	}

	static boolean isAble(int i, int j, int num) {
		for (int k = 0; k < 9; k++) {
			if (sudoku[i][k] == num)
				return false;
			if (sudoku[k][j] == num)
				return false;
			if (sudoku[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] == num)
				return false;
		}
		return true;
	}
}