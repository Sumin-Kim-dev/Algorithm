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
    boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        new BOJ4179().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        maze = new char[r][c];
        isVisited = new boolean[r][c][2];
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                 maze[i][j] = row.charAt(j);
                 if (maze[i][j] == 'J') {
                     isVisited[i][j][0] = true;
                     queue.offer(new int[]{i, j, 0});
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
                if (maze[i][j] == 'F') {
                    queue.offer(new int[]{i, j, 1});
                    isVisited[i][j][1] = true;
                }
            }
        }
        int time = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[2] == 0 && isVisited[curr[0]][curr[1]][1]) continue;
                if (curr[2] == 0 && (curr[0] == 0 || curr[0] == r - 1 || curr[1] == 0 || curr[1] == c - 1)) {
                    return time;
                }
                for (int[] dir : DIR) {
                    int[] next = {curr[0] + dir[0], curr[1] + dir[1], curr[2]};
                    if (next[0] < 0 || next[0] >= r || next[1] < 0 || next[1] >= c) continue;
                    if (maze[next[0]][next[1]] == '#') continue;
                    if (isVisited[next[0]][next[1]][next[2]] || isVisited[next[0]][next[1]][1]) continue;
                    isVisited[next[0]][next[1]][next[2]] = true;
                    queue.offer(next);
                }
            }
            time++;
        }
        return -1;
    }
}
