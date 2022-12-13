import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    int r, c;
    char[][] maze;
    Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        new BOJ4179().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        maze = new char[r][c];
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                maze[i][j] = row.charAt(j);
                if (maze[i][j] == 'F') {
                    queue.offer(new int[]{i, j, 1});
                }
            }
        }
        int time = solution();
        System.out.println(time > 0 ? time : "IMPOSSIBLE");
    }

    int solution() {
        final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == 'J') {
                    if (i == 0 || i == r - 1 || j == 0 || j == c - 1) return 1;
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }
        int time = 2;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : DIR) {
                    int[] next = {curr[0] + dir[0], curr[1] + dir[1], curr[2]};
                    if (next[0] < 0 || next[0] >= r || next[1] < 0 || next[1] >= c) continue;
                    if (maze[next[0]][next[1]] == '#' || maze[next[0]][next[1]] == 'F') continue;
                    if (next[2] == 0) {
                        if (next[0] == 0 || next[0] == r - 1 || next[1] == 0 || next[1] == c - 1) {
                            return time;
                        }
                        if (maze[next[0]][next[1]] == 'J') continue;
                        maze[next[0]][next[1]] = 'J';
                    }
                    if (next[2] == 1) {
                        maze[next[0]][next[1]] = 'F';
                    }
                    queue.offer(next);
                }
            }
            time++;
        }
        return -1;
    }
}
