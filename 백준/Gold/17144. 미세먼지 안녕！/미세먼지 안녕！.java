import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int[][] NEXT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int r, c, t;
    int[][] dust;
    ArrayList<Integer> cleanerPositions;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        dust = new int[r][c];
        cleanerPositions = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                dust[i][j] = Integer.parseInt(st.nextToken());
                if (dust[i][j] == -1) cleanerPositions.add(i);
            }
        }
    }

    void diffusion() {
        int[][] next = new int[r][c];
        int nextI, nextJ;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                next[i][j] += dust[i][j];
                if (dust[i][j] == -1) continue;
                for (int[] ints : NEXT) {
                    nextI = i + ints[0];
                    nextJ = j + ints[1];
                    if (!isValid(nextI, nextJ)) continue;
                    if (dust[nextI][nextJ] == -1) continue;
                    next[nextI][nextJ] += dust[i][j] / 5;
                    next[i][j] -= dust[i][j] / 5;
                }
            }
        }
        dust = next;
    }

    void cleaningUp() {
        int cleaner = cleanerPositions.get(0);
        for (int i = cleaner - 1; i > 0; i--) {
            dust[i][0] = dust[i - 1][0];
        }
        for (int j = 0; j < c - 1; j++) {
            dust[0][j] = dust[0][j + 1];
        }
        for (int i = 0; i < cleaner; i++) {
            dust[i][c - 1] = dust[i + 1][c - 1];
        }
        for (int j = c - 1; j > 1; j--) {
            dust[cleaner][j] = dust[cleaner][j - 1];
        }
        dust[cleaner][1] = 0;
    }

    void cleaningDown() {
        int cleaner = cleanerPositions.get(1);
        for (int i = cleaner + 1; i < r - 1; i++) {
            dust[i][0] = dust[i + 1][0];
        }
        for (int j = 0; j < c - 1; j++) {
            dust[r - 1][j] = dust[r - 1][j + 1];
        }
        for (int i = r - 1; i > cleaner; i--) {
            dust[i][c - 1] = dust[i - 1][c - 1];
        }
        for (int j = c - 1; j > 1; j--) {
            dust[cleaner][j] = dust[cleaner][j - 1];
        }
        dust[cleaner][1] = 0;
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    void solution() throws IOException {
        input();
        while (t-- > 0) {
            diffusion();
            cleaningUp();
            cleaningDown();
        }
        int currDust = 2;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                currDust += dust[i][j];
            }
        }
        System.out.println(currDust);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
