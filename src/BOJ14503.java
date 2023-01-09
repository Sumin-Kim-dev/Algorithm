import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
    static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int n, m;
    int[][] state;

    public static void main(String[] args) throws IOException {
        new BOJ14503().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        state = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                state[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(r, c, d));
    }

    int solution(int r, int c, int d) {
        int count = 0;
        while (true) {
            if (state[r][c] == 0) count++;
            state[r][c] = 2;
            boolean clean = false;
            for (int j = 1; j <= 4; j++) {
                if (clean) break;
                d = (d + 3) % 4;
                if (r + DIR[d][0] < 0 || r + DIR[d][1] >= n || c + DIR[d][0] < 0 || c + DIR[d][1] >= m) continue;
                if (state[r + DIR[d][0]][c + DIR[d][1]] > 0) continue;
                clean = true;
                r += DIR[d][0];
                c += DIR[d][1];
            }
            if (clean) continue;
            if (r - DIR[d][0] < 0 || r - DIR[d][1] >= n || c - DIR[d][0] < 0 || c - DIR[d][1] >= m) continue;
            if (state[r - DIR[d][0]][c - DIR[d][1]] == 1) break;
            r -= DIR[d][0];
            c -= DIR[d][1];
        }
        return count;
    }
}
