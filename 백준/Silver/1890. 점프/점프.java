import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n;
    int[][] board;
    long[][] count;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    long solution() {
        count = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count[i][j] = -1;
            }
        }
        return dp(0, 0);
    }

    long dp(int i, int j) {
        if (i == n - 1 && j == n - 1) return count[i][j] = 1;
        if (count[i][j] >= 0) return count[i][j];
        count[i][j] = 0;
        if (board[i][j] + i < n) count[i][j] += dp(board[i][j] + i, j);
        if (board[i][j] + j < n) count[i][j] += dp(i, board[i][j] + j);
        return count[i][j];
    }
}
