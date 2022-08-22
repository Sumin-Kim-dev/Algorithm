import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16927 {
    int n, m, r;
    int[][] arr;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void rotate(int j) {
        int total = 2 * n + 2 * m - 4 - 8 * j;
        ArrayList<int[]> curr = new ArrayList<>(total);
        for (int i = j; i < m - j; i++) {
            curr.add(new int[]{j, i, arr[j][i]});
        }
        for (int i = j + 1; i < n - 1 - j; i++) {
            curr.add(new int[]{i, m - 1 - j, arr[i][m - 1 - j]});
        }
        for (int i = m - j - 1; i >= j; i--) {
            curr.add(new int[]{n - 1 - j, i, arr[n - 1 - j][i]});
        }
        for (int i = n - 2 - j; i > j; i--) {
            curr.add(new int[]{i, j, arr[i][j]});
        }
        for (int i = 0; i < total; i++) {
            arr[curr.get(i)[0]][curr.get(i)[1]] = curr.get((i + r) % total)[2];
        }
    }

    void solution() throws IOException {
        input();
        for (int i = 0; i < n / 2 && i < m / 2; i++) {
            rotate(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ16927().solution();
    }
}
