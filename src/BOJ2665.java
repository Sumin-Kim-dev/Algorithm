import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ2665 {
    int n;
    int[][] map;

    public static void main(String[] args) throws IOException {
        new BOJ2665().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        System.out.println(solution());
    }

    int solution() {
        final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] isVisited = new boolean[n][n];
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{0, 0, 0});
        isVisited[0][0] = true;
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1], curr[2]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                if (isVisited[next[0]][next[1]]) continue;
                isVisited[next[0]][next[1]] = true;
                if (map[next[0]][next[1]] == 0) {
                    next[2]++;
                    deque.offerLast(next);
                } else deque.offerFirst(next);
                if (next[0] == n - 1 && next[1] == n - 1) return next[2];
            }
        }
        return 0;
    }
}
