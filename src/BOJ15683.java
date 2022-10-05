import java.io.*;
import java.util.*;

public class BOJ15683 {
    int n, m;
    int[][] cctv;
    ArrayList<int[]> cctvList = new ArrayList<>();

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] cctv = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cctv[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, m, cctv));
    }

    final int[][] DIRECTION = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int[] curr;
    int min;
    int walls;

    int solution(int n, int m, int[][] cctv) {
        this.n = n;
        this.m = m;
        this.cctv = cctv;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cctv[i][j] > 0 && cctv[i][j] < 6) cctvList.add(new int[]{i, j});
                if (cctv[i][j] == 6) walls++;
            }
        }
        curr = new int[cctvList.size()];
        min = n * m;
        backtracking(0);
        return min;
    }

    void backtracking(int depth) {
        if (depth == cctvList.size()) {
            int num = hide(curr);
            if (num < min) min = num;
            return;
        }
        for (int i = 0; i < 4; i++) {
            curr[depth] = i;
            backtracking(depth + 1);
        }
    }

    int hide(int[] curr) {
        final int[][] CCTV_DIR = {{0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};
        boolean[][] state = new boolean[n][m];
        int count = n * m - cctvList.size() - walls;
        for (int i = 0; i < cctvList.size(); i++) {
            int[] pos = cctvList.get(i);
            state[pos[0]][pos[1]] = true;
            int kind = cctv[pos[0]][pos[1]] - 1;
            for (int dir : CCTV_DIR[kind]) {
                int currI = pos[0];
                int currJ = pos[1];
                while (true) {
                    currI += DIRECTION[(dir + curr[i]) % 4][0];
                    currJ += DIRECTION[(dir + curr[i]) % 4][1];
                    if (currI < 0 || currI >= n || currJ < 0 || currJ >= m) break;
                    if (cctv[currI][currJ] == 6) break;
                    if (state[currI][currJ] || cctv[currI][currJ] > 0) continue;
                    state[currI][currJ] = true;
                    count--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        new BOJ15683().io();
    }
}
