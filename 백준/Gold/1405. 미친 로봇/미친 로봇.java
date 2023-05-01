import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int n;
    final static int[] ORDER = {0, 3, 1, 2};
    final static int[][] DIR = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    double[] probs = new double[4];

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            probs[ORDER[i]] = Integer.parseInt(st.nextToken()) / 100.0;
        }
        System.out.println(solution());
    }

    int[] curr;
    boolean[][] plane;

    double solution() {
        curr = new int[n];
        Arrays.fill(curr, -1);
        plane = new boolean[2 * n + 1][2 * n + 1];
        plane[n][n] = true;
        return backtracking(0, 0, n, n, 1);
    }

    double backtracking(int depth, double totalProb, int x, int y, double currProb) {
        if (depth == n) {
            return totalProb + currProb;
        }

        for (int i = 0; i < 4; i++) {
            if (depth > 0 && curr[depth - 1] + i == 3) continue;
            curr[depth] = i;
            int nextX = x + DIR[i][0];
            int nextY = y + DIR[i][1];
            if (!plane[nextX][nextY]) {
                plane[nextX][nextY] = true;
                totalProb = backtracking(depth + 1, totalProb, nextX, nextY, currProb * probs[i]);
                plane[nextX][nextY] = false;
            }
            curr[depth] = -1;
        }
        return totalProb;
    }
}
