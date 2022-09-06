import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14238 {
    int a, b, c;
    char[] answer;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        a = b = c = 0;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 'A' -> a++;
                case 'B' -> b++;
                case 'C' -> c++;
            }
        }
        answer = new char[a + b + c];
    }

    boolean[][][][][] dp;
    boolean[][][][][] isVisited;

    boolean dp(int a, int b, int c, int last2, int last) {
        if (isVisited[a][b][c][last2][last]) return dp[a][b][c][last2][last];
        isVisited[a][b][c][last2][last] = true;
        if (a == 0 && b == 0 && c == 0) return dp[a][b][c][last2][last] = true;
        if (!isPossible(a, b, c, last2, last)) return dp[a][b][c][last2][last] = false;
        if (last == 0) a--;
        else if (last == 1) b--;
        else if (last == 2) c--;
        if (dp(a, b, c, 0, last2)) {
            answer[a + b + c] = (char) ('A' + last);
            return dp[a][b][c][last2][last] = true;
        }
        if (last2 != 1 && dp(a, b, c, 1, last2)) {
            answer[a + b + c] = (char) ('A' + last);
            return dp[a][b][c][last2][last] = true;
        }
        if (last2 != 2 && last != 2 && dp(a, b, c, 2, last2)) {
            answer[a + b + c] = (char) ('A' + last);
            return dp[a][b][c][last2][last] = true;
        }
        return dp[a][b][c][last2][last] = false;
    }

    boolean isPossible(int a, int b, int c, int last2, int last) {
        if (last == 0) a--;
        else if (last == 1) b--;
        else if (last == 2) c--;
        if (a + b + c > 0) {
            if (last2 == 0) a--;
            else if (last2 == 1) b--;
            else if (last2 == 2) c--;
        }
        return a >= 0 && b >= 0 && c >= 0;
    }

    void solution() throws IOException {
        input();
        dp = new boolean[a + 1][b + 1][c + 1][3][3];
        boolean isAble = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                isVisited = new boolean[a + 1][b + 1][c + 1][3][3];
                if (i != 0 && i == j) continue;
                if (dp(a, b, c, i, j)) {
                    isAble = true;
                    break;
                }
            }
            if (isAble) break;
        }
        if (isAble) System.out.println(answer);
        else System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        new BOJ14238().solution();
    }
}
