import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {
    int n;
    int[][] result;

    public static void main(String[] args) throws IOException {
        new BOJ17281().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        result = new int[n][10];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    int[] order = new int[9];

    int solution() {
        order[3] = 1;
        return backtracking(2, 0);
    }

    int backtracking(int curr, int currMax) {
        if (curr == 10) {
            return Math.max(currMax, score());
        }
        for (int i = 0; i < 9; i++) {
            if (order[i] > 0) continue;
            order[i] = curr;
            currMax = Math.max(currMax, backtracking(curr + 1, currMax));
            order[i] = 0;
        }
        return currMax;
    }

    int score() {
        int score = 0;
        int currOrder = 0;
        for (int i = 0; i < n; i++) {
            Inning inning = scoreInning(i, currOrder);
            currOrder = inning.nextOrder;
            score += inning.score;
        }
        return score;
    }

    Inning scoreInning(int inning, int currOrder) {
        int score = 0;
        int out = 0;
        int[] base = new int[4];
        while (out < 3) {
            int curr = order[currOrder];
            currOrder = (currOrder + 1) % 9;
            int r = result[inning][curr];
            base[0] = curr;
            if (r == 0) {
                out++;
                continue;
            }
            for (int i = 3; i >= 0; i--) {
                if (base[i] == 0) continue;
                int runner = base[i];
                base[i] = 0;
                if (i + r > 3) {
                    score++;
                } else base[i + r] = runner;
            }
        }
        return new Inning(currOrder, score);
    }

    static class Inning {
        int nextOrder;
        int score;

        Inning(int nextOrder, int score) {
            this.nextOrder = nextOrder;
            this.score = score;
        }
    }
}
