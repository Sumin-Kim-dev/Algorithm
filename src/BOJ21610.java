import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21610 {
    public static final int[][] DIR = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    int n;
    int[][] a;

    public static void main(String[] args) throws IOException {
        new BOJ21610().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken()) - 1;
            move[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(move));
    }

    boolean[][] hasCloud;

    int solution(int[][] moveCommand) {
        hasCloud = new boolean[n][n];
        for (int i = n - 2; i < n; i++) {
            hasCloud[i][0] = true;
            hasCloud[i][1] = true;
        }
        for (int[] move : moveCommand) {
            hasCloud = move(move);
        }
        return Arrays.stream(a)
                .mapToInt(i -> Arrays.stream(i).sum())
                .sum();
    }

    boolean[][] move(int[] move) {
        boolean[][] currCloud = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nextI = (i + (n + DIR[move[0]][0]) * move[1]) % n;
                int nextJ = (j + (n + DIR[move[0]][1]) * move[1]) % n;
                currCloud[nextI][nextJ] = hasCloud[i][j];
                if (currCloud[nextI][nextJ]) a[nextI][nextJ]++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!currCloud[i][j]) continue;
                for (int k = 1; k < 8; k += 2) {
                    int nextI = i + DIR[k][0];
                    int nextJ = j + DIR[k][1];
                    if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n) continue;
                    if (a[nextI][nextJ] > 0) a[i][j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (currCloud[i][j]) {
                    currCloud[i][j] = false;
                    continue;
                }
                if (a[i][j] >= 2) {
                    currCloud[i][j] = true;
                    a[i][j] -= 2;
                }
            }
        }
        return currCloud;
    }
}
