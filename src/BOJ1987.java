import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
    final int[][] NEXT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int r, c;
    char[][] board;
    boolean[] isChecked = new boolean[26];

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        String row;
        board = new char[r][];
        for (int i = 0; i < r; i++) {
            row = br.readLine();
            board[i] = row.toCharArray();
        }
    }

    int maxLength = 0, length = 0;

    void backtracking(int i, int j) {
        isChecked[board[i][j] - 'A'] = true;
        length++;
        if (length > maxLength) maxLength = length;
        int nextI, nextJ;
        for (int k = 0; k < 4; k++) {
            nextI = i + NEXT[k][0];
            nextJ = j + NEXT[k][1];
            if (!isValid(nextI, nextJ)) continue;
            if (isChecked[board[nextI][nextJ] - 'A']) continue;
            backtracking(nextI, nextJ);
        }
        isChecked[board[i][j] - 'A'] = false;
        length--;
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    void solution() throws IOException {
        input();
        backtracking(0, 0);
        System.out.println(maxLength);
    }

    public static void main(String[] args) throws IOException {
        new BOJ1987().solution();
    }
}
