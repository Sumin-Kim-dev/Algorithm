import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    int[][] maze;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(solution());
    }

    int solution() {
        final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] isVisited = new boolean[n][m];
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{0, 0, 0});
        isVisited[0][0] = true;
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            for (int[] dir : DIRECTION) {
                int nextI = curr[0] + dir[0];
                int nextJ = curr[1] + dir[1];
                if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                if (nextI == n - 1 && nextJ == m - 1) {
                    return curr[2] + maze[nextI][nextJ];
                }
                if (isVisited[nextI][nextJ]) continue;
                isVisited[nextI][nextJ] = true;
                if (maze[nextI][nextJ] == 0) {
                    deque.offerFirst(new int[]{nextI, nextJ, curr[2]});
                } else {
                    deque.offerLast(new int[]{nextI, nextJ, curr[2] + 1});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new Main().io();
    }
}
