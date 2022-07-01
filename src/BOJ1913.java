import java.io.*;

public class BOJ1913 {
    int n, num, numX, numY;
    int[][] table;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = Integer.parseInt(br.readLine());
        table = new int[n][n];
    }

    void makeTable() {
        int x = (n - 1) / 2, y = (n - 1) / 2;
        if (num == 1) {
            numX = x;
            numY = y;
        }
        table[x--][y] = 1;
        for (int i = 1; i < n; i += 2) {
            for (int j = i * i + 1; j <= (i + 2) * (i + 2); j++) {
                int k = (j - i * i) / (i + 1);
                if (num == j) {
                    numX = x;
                    numY = y;
                }
                switch (k) {
                    case 0 -> table[x][y++] = j;
                    case 1 -> table[x++][y] = j;
                    case 2 -> table[x][y--] = j;
                    default -> table[x--][y] = j;
                }
            }
        }
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(table[i][j]).append(' ');
            sb.append('\n');
        }
        sb.append(numX + 1).append(' ').append(numY + 1);
        bw.write(sb.toString());
        bw.close();
    }

    void solution() throws IOException {
        input();
        makeTable();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1913().solution();
    }
}
