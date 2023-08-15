import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] paper;

    static int white;
    static int blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count(0, 0, n);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void count(int r, int c, int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += paper[r + i][c + j];
            }
        }
        if (sum == 0) {
            white++;
            return;
        }
        if (sum == size * size) {
            blue++;
            return;
        }
        count(r, c, size / 2);
        count(r, c + size / 2, size / 2);
        count(r + size / 2, c, size / 2);
        count(r + size / 2, c + size / 2, size / 2);
    }
}