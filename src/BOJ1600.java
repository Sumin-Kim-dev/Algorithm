import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {
    int k;
    int w, h;
    int[][] board;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    int solution() {
        if (w == 1 && h == 1) return 0;
        final int[][] HORSE = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int[][][] dist = new int[h][w][k + 1];
        queue.offer(new int[]{0, 0, 0}); // 좌표 2개, 말의 움직임 횟수
        dist[0][0][0] = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIR) {
                int nextI = curr[0] + dir[0];
                int nextJ = curr[1] + dir[1];
                if (nextI < 0 || nextI >= h || nextJ < 0 || nextJ >= w) continue;
                if (board[nextI][nextJ] == 1) continue;
                if (dist[nextI][nextJ][curr[2]] > 0) continue;
                dist[nextI][nextJ][curr[2]] = dist[curr[0]][curr[1]][curr[2]] + 1;
                if (nextI == h - 1 && nextJ == w - 1) return dist[nextI][nextJ][curr[2]] - 1;
                queue.offer(new int[]{nextI, nextJ, curr[2]});
            }
            if (curr[2] == k) continue;
            for (int[] dir : HORSE) {
                int nextI = curr[0] + dir[0];
                int nextJ = curr[1] + dir[1];
                if (nextI < 0 || nextI >= h || nextJ < 0 || nextJ >= w) continue;
                if (board[nextI][nextJ] == 1) continue;
                if (dist[nextI][nextJ][curr[2] + 1] > 0) continue;
                dist[nextI][nextJ][curr[2] + 1] = dist[curr[0]][curr[1]][curr[2]] + 1;
                if (nextI == h - 1 && nextJ == w - 1) return dist[nextI][nextJ][curr[2] + 1] - 1;
                queue.offer(new int[]{nextI, nextJ, curr[2] + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new BOJ1600().io();
    }
}
