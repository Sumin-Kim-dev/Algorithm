import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17406 {
    int n, m, k;
    int[][] a, curr, rotates;

    public static void main(String[] args) throws IOException {
        new BOJ17406().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotates = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotates[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotates[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotates[i][2] = Integer.parseInt(st.nextToken());
        }
        solution();
        System.out.println(min);
    }

    int min = Integer.MAX_VALUE;
    int[] seq;
    boolean[] isUsed;

    void solution() {
        seq = new int[k];
        isUsed = new boolean[k];
        curr = new int[n][m];
        backtracking(0);
    }

    void backtracking(int depth) {
        if (depth == k) {
            copy(a, curr);
            for (int i : seq) {
                rotate(curr, rotates[i]);
            }
            min = Math.min(min, size(curr));
            return;
        }
        for (int i = 0; i < k; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            seq[depth] = i;
            backtracking(depth + 1);
            isUsed[i] = false;
        }
    }

    void copy(int[][] a, int[][] b) {
        for (int i = 0; i < n; i++) {
            b[i] = Arrays.copyOf(a[i], a[i].length);
        }
    }

    void rotate(int[][] a, int[] rotate) {
        for (int k = 1; k <= rotate[2]; k++) {
            int temp = a[rotate[0] - k][rotate[1] + k];
            for (int j = rotate[1] + k; j > rotate[1] - k; j--) {
                a[rotate[0] - k][j] = a[rotate[0] - k][j - 1];
            }
            for (int i = rotate[0] - k; i < rotate[0] + k; i++) {
                a[i][rotate[1] - k] = a[i + 1][rotate[1] - k];
            }
            for (int j = rotate[1] - k; j < rotate[1] + k; j++) {
                a[rotate[0] + k][j] = a[rotate[0] + k][j + 1];
            }
            for (int i = rotate[0] + k; i > rotate[0] - k; i--) {
                a[i][rotate[1] + k] = a[i - 1][rotate[1] + k];
                if (i - 1 == rotate[0] - k) a[i][rotate[1] + k] = temp;
            }
        }
    }

    int size(int[][] curr) {
        return Arrays.stream(curr).mapToInt(row -> Arrays.stream(row).sum()).min().orElse(0);
    }
}
