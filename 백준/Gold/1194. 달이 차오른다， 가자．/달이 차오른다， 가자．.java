import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    char[][] maze;
    int[][][] minDist;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new char[n][m];
        int[] start = new int[3];
        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == '0') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        System.out.println(bfs(start));
    }

    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int bfs(int[] start) {
        minDist = new int[n][m][1 << 6];
        minDist[start[0]][start[1]][start[2]] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1], curr[2]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= m) continue;
                if (maze[next[0]][next[1]] == '#') continue;
                if (maze[next[0]][next[1]] == '1') return minDist[curr[0]][curr[1]][curr[2]];
                if (Character.isUpperCase(maze[next[0]][next[1]])) {
                    int door = maze[next[0]][next[1]] - 'A';
                    if ((next[2] & (1 << door)) == 0) continue;
                }
                if (Character.isLowerCase(maze[next[0]][next[1]])) {
                    int key = maze[next[0]][next[1]] - 'A';
                    next[2] |= (1 << key);
                }
                if (minDist[next[0]][next[1]][next[2]] > 0) continue;
                minDist[next[0]][next[1]][next[2]] = minDist[curr[0]][curr[1]][curr[2]] + 1;
                queue.offer(next);
            }
        }
        return -1;
    }
}
