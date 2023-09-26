import java.io.*;
import java.util.*;

public class Main {

    int[][] sudoku;
    ArrayList<Integer> blank = new ArrayList<>(); // 빈 칸의 인덱스 저장

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String row = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = row.charAt(j) - '0';
                if (sudoku[i][j] == 0)
                    blank.add(i * 9 + j);
            }
        }
    }

    void solve(int depth) {
        StringBuilder sb = new StringBuilder();
        if (depth == blank.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
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

    boolean isAble(int i, int j, int num) {
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

    void solution() throws IOException {
        input();
        solve(0);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
