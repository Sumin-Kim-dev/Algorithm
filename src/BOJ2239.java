import java.io.*;
import java.util.*;

public class BOJ2239 {

    int[][] sudoku;
    boolean[][] rows, columns, squares; // 행, 열, 3*3 정사각형에 숫자가 있는지 확인
    ArrayList<Integer> blank = new ArrayList<>(); // 빈 칸의 인덱스 저장

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        rows = new boolean[9][9];
        columns = new boolean[9][9];
        squares = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            String row = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = row.charAt(j) - '0';
                if (sudoku[i][j] == 0) {
                    blank.add(i * 9 + j);
                    continue;
                }
                edit(i, j, sudoku[i][j]);
            }
        }
    }

    void edit(int i, int j, int num) {
        num--;
        rows[i][num] = !rows[i][num];
        columns[j][num] = !columns[j][num];
        squares[i / 3 * 3 + j / 3][num] = !squares[i / 3 * 3 + j / 3][num];
    }

    void solve(int depth) {
        if (depth == blank.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        int row = blank.get(depth) / 9;
        int col = blank.get(depth) % 9;
        for (int i = 1; i <= 9; i++) {
            if (isAble(row, col, i)) {
                sudoku[row][col] = i;
                edit(row, col, i);
                solve(depth + 1);
                edit(row, col, i);
            }
        }
        sudoku[row][col] = 0;
    }

    boolean isAble(int i, int j, int num) {
        num--;
        return !(rows[i][num] || columns[j][num] || squares[i / 3 * 3 + j / 3][num]);
    }

    void solution() throws IOException {
        input();
        solve(0);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2239().solution();
    }
}
