import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        new Main().solution(r1, c1, r2, c2);
    }

    void solution(int r1, int c1, int r2, int c2) throws IOException {
        int[][] table = new int[r2 - r1 + 1][c2 - c1 + 1];
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                int k = Math.max(Math.abs(i), Math.abs(j));
                if (j == k) table[i - r1][j - c1] = (2 * k - 1) * (2 * k - 1) + (k - i);
                if (i == -k) table[i - r1][j - c1] = (2 * k - 1) * (2 * k - 1) + (3 * k - j);
                if (j == -k) table[i - r1][j - c1] = (2 * k - 1) * (2 * k - 1) + 5 * k + i;
                if (i == k) table[i - r1][j - c1] = (2 * k - 1) * (2 * k - 1) + 7 * k + j;
            }
        }
        print(table, length(table));
    }

    int length(int[][] table) {
        int length = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                length = Math.max(length, String.valueOf(table[i][j]).length());
            }
        }
        return length;
    }

    void print(int[][] table, int length) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                int padding = length - String.valueOf(table[i][j]).length();
                bw.append(" ".repeat(padding)).append(String.valueOf(table[i][j])).append(' ');
            }
            bw.newLine();
        }
        bw.close();
    }
}
