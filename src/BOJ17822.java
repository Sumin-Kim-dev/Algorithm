import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17822 {
    int n, m;
    int[][] circles;
    int[] start;
    int[][] rotations;

    public static void main(String[] args) throws IOException {
        new BOJ17822().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        circles = new int[n][m];
        start = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                circles[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotations = new int[t][3];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rotations[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    int solution() {
        for (int[] rotation : rotations) {
            rotate(rotation);
            if (delete()) continue;
            after();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += circles[i][j];
            }
        }
        return sum;
    }

    void rotate(int[] rotation) {
        int move = rotation[1] == 0 ? -rotation[2] : rotation[2];
        for (int i = rotation[0] - 1; i < n; i += rotation[0]) {
            start[i] = (start[i] + m + move) % m;
        }
    }

    boolean[][] isVisited;

    boolean delete() {
        isVisited = new boolean[n][m];
        boolean delete = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = circles[i][(j + start[i]) % m];
                if (curr == 0) continue;
                delete |= delete(i, j, curr, false);
            }
        }
        return delete;
    }

    boolean delete(int i, int j, int curr, boolean isDeleted) {
        if (isVisited[i][(j + start[i]) % m]) return false;
        isVisited[i][(j + start[i]) % m] = true;
        if (circles[i][(j + start[i] + 1) % m] == curr) {
            delete(i, j + 1, curr, true);
            isDeleted = true;
        }
        if (circles[i][(j + start[i] + m - 1) % m] == curr) {
            delete(i, j + m - 1, curr, true);
            isDeleted = true;
        }
        if (i + 1 < n && circles[i + 1][(j + start[i + 1]) % m] == curr) {
            delete(i + 1, j, curr, true);
            isDeleted = true;
        }
        if (i > 0 && circles[i - 1][(j + start[i - 1]) % m] == curr) {
            delete(i - 1, j, curr, true);
            isDeleted = true;
        }
        if (isDeleted) circles[i][(j + start[i]) % m] = 0;
        return isDeleted;
    }

    double mean() {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (circles[i][j] == 0) continue;
                sum += circles[i][j];
                count++;
            }
        }
        return sum * 1.0 / count;
    }

    void after() {
        double mean = mean();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (circles[i][j] == 0) continue;
                if (circles[i][j] > mean) circles[i][j]--;
                else if (circles[i][j] < mean) circles[i][j]++;
            }
        }
    }
}
