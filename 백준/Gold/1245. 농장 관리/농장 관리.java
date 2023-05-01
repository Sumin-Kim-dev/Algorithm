import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] heights = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, m, heights));
    }

    int solution(int n, int m, int[][] heights) {
        int count = 0;
        boolean[][] isChecked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (heights[i][j] == 0) isChecked[i][j] = true;
                if (isChecked[i][j]) continue;
                if (bfs(n, m, heights, i, j, isChecked)) count++;
            }
        }
        return count;
    }

    boolean bfs(int n, int m, int[][] heights, int i, int j, boolean[][] isChecked) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        isChecked[i][j] = true;
        int peakHeight = heights[i][j];
        boolean isPeak = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (curr[0] + dx < 0 || curr[0] + dx >= n || curr[1] + dy < 0 || curr[1] + dy >= m) continue;
                    if (dx == 0 && dy == 0) continue;
                    int nextHeight = heights[curr[0] + dx][curr[1] + dy];
                    if (nextHeight == 0) continue;
                    if (peakHeight < nextHeight) isPeak = false;
                    if (peakHeight == nextHeight) {
                        if (isChecked[curr[0] + dx][curr[1] + dy]) continue;
                        queue.offer(new int[]{curr[0] + dx, curr[1] + dy});
                        isChecked[curr[0] + dx][curr[1] + dy] = true;
                    }
                }
            }
        }
        return isPeak;
    }
}
