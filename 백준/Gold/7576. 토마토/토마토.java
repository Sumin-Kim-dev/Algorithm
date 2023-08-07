import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) count++;
                else if (map[i][j] == 1) queue.offer(new int[]{i, j});
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : deltas) {
                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc] != 0) continue;
                map[nr][nc] = map[r][c] + 1;
                queue.offer(new int[] {nr, nc});
                if (result < map[nr][nc] - 1) result = map[nr][nc] - 1;
                count--;
            }
        }

        System.out.println(count > 0 ? -1 : result);

    }
}
