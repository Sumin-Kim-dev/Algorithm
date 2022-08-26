import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404 {
    final int INF = 1_000_001;
    int n;
    int[][] painting;
    int[][] totalCost;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        painting = new int[n][3];
        totalCost = new int[3][3];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            painting[i][0] = Integer.parseInt(st.nextToken());
            painting[i][1] = Integer.parseInt(st.nextToken());
            painting[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    void solution() throws IOException {
        input();
        initialize();
        for (int i = 2; i < n; i++) {
            dp(i);
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == i) continue;
                if (minCost > totalCost[i][j]) {
                    minCost = totalCost[i][j];
                }
            }
        }
        System.out.println(minCost);
    }

    void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == i) totalCost[i][j] = INF;
                else totalCost[i][j] = painting[0][i] + painting[1][j];
            }
        }
    }

    void dp(int curr) {
        int[][] temp = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = INF;
                for (int k = 0; k < 3; k++) {
                    if (k == j) continue;
                    if (temp[i][j] > totalCost[i][k] + painting[curr][j]) {
                        temp[i][j] = totalCost[i][k] + painting[curr][j];
                    }
                }
            }
        }
        totalCost = temp;
    }

    public static void main(String[] args) throws IOException {
        new BOJ17404().solution();
    }
}
