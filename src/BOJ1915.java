import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
    int n, m;
    int[][] arr;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = row.charAt(j) - '0';
            }
        }
    }

    boolean is22Square(int i, int j) {
        return arr[i][j] == 1 && arr[i + 1][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j + 1] == 1;
    }

    boolean arrHas1() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) count++;
            }
        }
        return count != 0;
    }

    int maxSquareSize() {
        int size = 1;
        boolean arrHasSize = arrHas1();
        if (!arrHasSize) return 0;
        while (true) {
            int count = 0;
            for (int i = 0; i < n - size; i++) {
                for (int j = 0; j < m - size; j++) {
                    if (is22Square(i, j)) {
                        arr[i][j] = 1;
                        count++;
                    } else arr[i][j] = 0;
                }
            }
            if (count == 0) break;
            else size++;
        }
        return size * size;
    }

    void solution() throws IOException {
        input();
        System.out.println(maxSquareSize());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1915().solution();
    }
}
