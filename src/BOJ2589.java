import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {
    int n, m;
    char[][] treasure;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] treasure = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            treasure[i] = str.toCharArray();
        }
        System.out.println(solution(n, m, treasure));
    }

    int solution(int n, int m, char[][] treasure) {
        this.n = n;
        this.m = m;
        this.treasure = treasure;
        Queue<int[]> ground = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (treasure[i][j] == 'L') ground.offer(new int[]{i, j});
            }
        }
        final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int max = 0;
        while (!ground.isEmpty()) {
            int[] curr = ground.poll();
            queue.offer(curr);
            int[][] dist = new int[n][m];
            dist[curr[0]][curr[1]] = 1;
            while (!queue.isEmpty()) {
                int[] c = queue.poll();
                for (int[] dir : DIRECTION) {
                    int nextI = c[0] + dir[0];
                    int nextJ = c[1] + dir[1];
                    if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                    if (treasure[nextI][nextJ] == 'W') continue;
                    if (dist[nextI][nextJ] > 0) continue;
                    dist[nextI][nextJ] = dist[c[0]][c[1]] + 1;
                    queue.offer(new int[]{nextI, nextJ});
                    if (dist[nextI][nextJ] > max) max = dist[nextI][nextJ];
                }
            }
        }
        return max - 1;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2589().io();
    }
}
