import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    int[][] table;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        table = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                table[i][j] = row.charAt(j) - '0';
            }
        }
        System.out.println(solution());
    }

    int solution() {
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d1 = -i; d1 <= n - i; d1++) {
                    for (int d2 = -j; d2 <= m - j; d2++) {
                        max = Math.max(max, seq(i, j, d1, d2));
                    }
                }
            }
        }
        return max;
    }

    int seq(int i, int j, int d1, int d2) {
        int max = -1;
        int currI = i;
        int currJ = j;
        int curr = 0;
        while (currI >= 0 && currI < n && currJ >= 0 && currJ < m) {
            curr += table[currI][currJ];
            int sqrt = (int) Math.sqrt(curr);
            if (sqrt * sqrt == curr) max = curr;
            if (d1 == 0 && d2 == 0) break;
            currI += d1;
            currJ += d2;
            curr *= 10;
        }
        return max;
    }
}
