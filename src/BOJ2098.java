import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2098 {
    final int NOT_YET = 100_000_000;
    final int IMPOSSIBLE = 20_000_000;
    int n;
    int[][] cost;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int dp() {
        int[][] minCost = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                minCost[i][j] = NOT_YET;
            }
        }
        minCost[0][0] = 0;
        return dp(0, (1 << n) - 1, minCost);
    }

    int dp(int i, int bit, int[][] minCost) {
        if (minCost[i][bit] != NOT_YET) return minCost[i][bit];
        minCost[i][bit] = IMPOSSIBLE;
        if (bit != 0 && (bit & (1 << i)) == 0) return minCost[i][bit];
        for (int m = 0; m < n; m++) {
            if (cost[m][i] == 0) continue;
            int before = dp(m, bit ^ (1 << i), minCost);
            if (minCost[i][bit] > before + cost[m][i]) {
                minCost[i][bit] = before + cost[m][i];
            }
        }
        return minCost[i][bit];
    }

    void solution() throws IOException {
        input();
        System.out.println(dp());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2098().solution();
    }
}
