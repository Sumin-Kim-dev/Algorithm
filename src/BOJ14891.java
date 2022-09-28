import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] states = new char[4][];
        for (int i = 0; i < 4; i++) {
            states[i] = br.readLine().toCharArray();
        }
        int k = Integer.parseInt(br.readLine());
        int[][] rotates = new int[k][2];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotates[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotates[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(states, rotates));
    }

    int solution(char[][] states, int[][] rotates) {
        int[] curr = new int[4];
        for (int[] rotate : rotates) {
            int[] move = new int[3];
            for (int j = 1; j <= 3; j++) {
                if (states[j - 1][(curr[j - 1] + 2) % 8] != states[j][(curr[j] + 6) % 8])
                    move[j - 1] = -1;
            }
            curr = nextState(curr, move, rotate);
        }
        int answer = 0;
        for (int i = 3; i >= 0; i--) {
            answer *= 2;
            answer += states[i][curr[i]] - '0';
        }
        return answer;
    }

    int[] nextState(int[] curr, int[] move, int[] rotate) {
        int[] next = new int[4];
        next[rotate[0]] = rotate[1];
        next = dfs(rotate[0], move, next);
        for (int i = 0; i < 4; i++) {
            curr[i] = (curr[i] - next[i] + 8) % 8;
        }
        return curr;
    }

    int[] dfs(int i, int[] move, int[] next) {
        if (i > 0 && move[i - 1] == -1 && next[i - 1] == 0) {
            next[i - 1] = -next[i];
            dfs(i - 1, move, next);
        }
        if (i < 3 && move[i] == -1 && next[i + 1] == 0) {
            next[i + 1] = -next[i];
            dfs(i + 1, move, next);
        }
        return next;
    }

    public static void main(String[] args) throws IOException {
        new BOJ14891().io();
    }
}
