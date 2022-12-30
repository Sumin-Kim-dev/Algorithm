import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ16973 {
    int n, m, h, w;
    int[][] map;

    public static void main(String[] args) throws IOException {
        new BOJ16973().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        int[] start = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        int[] end = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        System.out.println(solution(start, end));
    }

    int solution(int[] start, int[] end) {
        if (start[0] == end[0] && start[1] == end[1]) return 0;
        final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] isVisited = new boolean[n][m];
        isVisited[start[0]][start[1]] = true;
        int min = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : DIR) {
                    int[] next = new int[]{curr[0] + dir[0], curr[1] + dir[1]};
                    if (next[0] < 0 || next[0] > n - h || next[1] < 0 || next[1] > m - w) continue;
                    if (isVisited[next[0]][next[1]]) continue;
                    isVisited[next[0]][next[1]] = true;
                    if (isAble(curr, dir)) {
                        if (next[0] == end[0] && next[1] == end[1]) return min;
                        queue.offer(next);
                    }
                }
            }
            min++;
        }
        return -1;
    }

    boolean isAble(int[] p, int[] dir) {
        if (dir[0] == 0) {
            int check = dir[1] == 1 ? p[1] + w : p[1] - 1;
            for (int i = 0; i < h; i++) {
                if (map[p[0] + i][check] == 1) return false;
            }
            return true;
        }
        int check = dir[0] == 1 ? p[0] + h : p[0] - 1;
        for (int i = 0; i < w; i++) {
            if (map[check][p[1] + i] == 1) return false;
        }
        return true;
    }
}
