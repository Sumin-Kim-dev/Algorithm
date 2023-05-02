import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    int[][] maze;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = str.charAt(j) - 'A';
            }
        }
        System.out.println(bfs());
    }

    int bfs() {
        final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][][] minTime = new int[n][m][1 << (n + m)];
        minTime[0][0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] next = {curr[0] + DIR[i][0], curr[1] + DIR[i][1], curr[2]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= m) continue;
                if (minTime[next[0]][next[1]][next[2]] > 0) continue;
                if (isAble(curr, i) && isAble(next, i)) {
                    if (next[0] == n - 1 && next[1] == m - 1) return minTime[curr[0]][curr[1]][curr[2]];
                    minTime[next[0]][next[1]][next[2]] = minTime[curr[0]][curr[1]][curr[2]] + 1;
                    queue.offer(next);
                }
            }
            int[] next = {curr[0], curr[1], curr[2] ^ ((1 << curr[0]) + (1 << (curr[1] + n)))};
            if (minTime[next[0]][next[1]][next[2]] > 0) continue;
            minTime[next[0]][next[1]][next[2]] = minTime[curr[0]][curr[1]][curr[2]] + 1;
            queue.offer(next);
        }
        return -1;
    }

    int currType(int[] curr) {
        boolean isRotate = (curr[2] & (1 << curr[0])) != 0 ^ (curr[2] & (1 << (curr[1] + n))) != 0;
        if (maze[curr[0]][curr[1]] >= 2 && isRotate) return 5 - maze[curr[0]][curr[1]];
        return maze[curr[0]][curr[1]];
    }

    boolean isAble(int[] curr, int dir) {
        int currType = currType(curr);
        if (dir / 2 == 0) return currType == 0 || currType == 2;
        return currType == 0 || currType == 3;
    }
}
