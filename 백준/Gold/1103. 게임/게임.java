import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 10000;

    int n, m;
    int[][] board;
    int[][] maxCount;
    boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        maxCount = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                maxCount[i][j] = -1;
                if (row.charAt(j) == 'H') board[i][j] = -1;
                else board[i][j] = row.charAt(j) - '0';
            }
        }
        System.out.println(solution());
    }

    int solution() {
        int result = dfs(0, 0);
        return result == INF ? -1 : result;
    }

    int dfs(int i, int j) {
        if (board[i][j] == -1) return maxCount[i][j] = 0;
        if (isVisited[i][j]) return INF;
        if (maxCount[i][j] >= 0) return maxCount[i][j];
        isVisited[i][j] = true;
        maxCount[i][j] = 0;
        if (i + board[i][j] < n) maxCount[i][j] = Math.max(maxCount[i][j], dfs(i + board[i][j], j));
        if (i - board[i][j] >= 0) maxCount[i][j] = Math.max(maxCount[i][j], dfs(i - board[i][j], j));
        if (j + board[i][j] < m) maxCount[i][j] = Math.max(maxCount[i][j], dfs(i, j + board[i][j]));
        if (j - board[i][j] >= 0) maxCount[i][j] = Math.max(maxCount[i][j], dfs(i, j - board[i][j]));
        isVisited[i][j] = false;
        if (maxCount[i][j] == INF) return INF;
        return ++maxCount[i][j];
    }
}
