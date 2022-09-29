import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    final int INF = 1000000;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] state = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                state[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, m, state));
    }

    int solution(int n, int m, int[][] state) {
        int[][] viruses = setViruses(n, state);
        int[][][] time = new int[viruses.length][n][n];
        for (int i = 0; i < viruses.length; i++) {
            time[i] = setTime(viruses[i], n, state);
        }
        int[] curr = new int[m];
        int answer = backtracking(0, m, n, curr, time, state, INF);
        return answer == INF ? -1 : answer;
    }

    int backtracking(int depth, int m, int n, int[] curr, int[][][] time, int[][] state, int answer) {
        if (depth == m) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (state[i][j] != 0) {
                        continue;
                    }
                    int minTime = answer;
                    for (int k = 0; k < m; k++) {
                        if (minTime > time[curr[k]][i][j]) minTime = time[curr[k]][i][j];
                    }
                    if (minTime > max) max = minTime;
                }
            }
            return Math.min(answer, max);
        }
        int start = 0;
        if (depth > 0) start = curr[depth - 1] + 1;
        for (int i = start; i < time.length; i++) {
            curr[depth] = i;
            answer = backtracking(depth + 1, m, n, curr, time, state, answer);
        }
        return answer;
    }

    int[][] setTime(int[] virus, int n, int[][] state) {
        final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] time = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                time[i][j] = INF;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(virus);
        time[virus[0]][virus[1]] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] direction : directions) {
                int nextI = curr[0] + direction[0];
                int nextJ = curr[1] + direction[1];
                if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n) continue;
                if (state[nextI][nextJ] == 1) continue;
                if (time[nextI][nextJ] < INF) continue;
                time[nextI][nextJ] = time[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{nextI, nextJ});
            }
        }
        return time;
    }

    int[][] setViruses(int n, int[][] state) {
        ArrayList<int[]> viruses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (state[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }
        return viruses.toArray(new int[viruses.size()][2]);
    }

    public static void main(String[] args) throws IOException {
        new BOJ17142().io();
    }
}
