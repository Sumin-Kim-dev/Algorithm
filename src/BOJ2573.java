import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
    int n, m;
    int[][] ice;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] ice = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, m, ice));
    }

    int solution(int n, int m, int[][] ice) {
        this.n = n;
        this.m = m;
        this.ice = ice;
        int time = 0;
        while (next()) {
            time++;
            if (!isConnected()) return time;
        }
        return 0;
    }

    final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    boolean next() {
        boolean hasNext = false;
        int[][] next = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ice[i][j] == 0) continue;
                next[i][j] = ice[i][j];
                for (int[] dir : DIRECTION) {
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];
                    if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                    if (ice[nextI][nextJ] == 0 && next[i][j] > 0) next[i][j]--;
                }
                if (next[i][j] > 0) hasNext = true;
            }
        }
        ice = next;
        return hasNext;
    }

    boolean isConnected() {
        ArrayList<int[]> icePos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ice[i][j] > 0) icePos.add(new int[]{i, j});
            }
        }
        boolean[][] isChecked = new boolean[n][m];
        isChecked[icePos.get(0)[0]][icePos.get(0)[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(icePos.get(0));
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIRECTION) {
                int nextI = curr[0] + dir[0];
                int nextJ = curr[1] + dir[1];
                if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                if (isChecked[nextI][nextJ]) continue;
                isChecked[nextI][nextJ] = true;
                if (ice[nextI][nextJ] > 0) {
                    queue.add(new int[]{nextI, nextJ});
                    cnt++;
                }
            }
        }
        return cnt == icePos.size();
    }

    public static void main(String[] args) throws IOException {
        new BOJ2573().io();
    }
}
