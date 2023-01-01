import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    int n, h;
    boolean[][] ladders;

    public static void main(String[] args) throws IOException {
        new BOJ15684().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        ladders = new boolean[h][n - 1];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            ladders[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }
        System.out.println(solution());
    }

    int solution() {
        if (finish(ladders)) return 0;
        backtracking(1, 0, 0);
        return min <= 3 ? min : -1;
    }

    int min = 4;
    void backtracking(int depth, int startI, int startJ) {
        if (depth >= min) return;
        for (int i = startI; i < h; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (i == startI && j < startJ) continue;
                if (ladders[i][j]) continue;
                if (j < n - 2 && ladders[i][j + 1]) continue;
                if (j > 0 && ladders[i][j - 1]) continue;
                ladders[i][j] = true;
                if (finish(ladders)) {
                    min = Math.min(min, depth);
                } else {
                    backtracking(depth + 1, i, j + 1);
                }
                ladders[i][j] = false;
            }
        }
    }

    boolean finish(boolean[][] ladders) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = i;
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (!ladders[i][j]) continue;
                int temp = result[j];
                result[j] = result[j + 1];
                result[j + 1] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (result[i] != i) return false;
        }
        return true;
    }
}
