import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {
    int r, c;
    char[][] grid;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        grid = new char[r][];
        for (int i = 0; i < r; i++) {
            grid[i] = br.readLine().toCharArray();
        }
    }

    boolean finished;
    boolean[][] isChecked;
    int count = 0;

    void backtracking(int i, int j) {
        if (finished) return;
        isChecked[i][j] = true;
        if (j == c - 1) {
            finished = true;
            count++;
            return;
        }
        for (int k = -1; k <= 1; k++) {
            if (finished) continue;
            if (i + k < 0 || i + k >= r) continue;
            if (grid[i + k][j + 1] == 'x' || isChecked[i + k][j + 1]) continue;
            grid[i + k][j + 1] = 'x';
            backtracking(i + k, j + 1);
            if (!finished) grid[i + k][j + 1] = '.';
        }
    }

    void solution() throws IOException {
        input();
        isChecked = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            if (grid[i][0] == 'x') continue;
            finished = false;
            backtracking(i, 0);
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ3109().solution();
    }
}
