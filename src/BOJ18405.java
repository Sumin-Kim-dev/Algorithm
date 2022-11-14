import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18405 {
    int n, k;
    int[][] virus;

    public static void main(String[] args) throws IOException {
        new BOJ18405().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        virus = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                virus[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        System.out.println(solution(s, x - 1, y - 1));
    }

    int solution(int s, int x, int y) {
        final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{virus[x][y], x, y, 0});
        boolean[][] isVisited = new boolean[n][n];
        isVisited[x][y] = true;
        int answer = 0;
        int minDist = -1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] > 0) {
                if (minDist == -1) {
                    answer = curr[0];
                    minDist = curr[3];
                    continue;
                }
                if (curr[3] > minDist) break;
                if (curr[0] < answer) answer = curr[0];
            }
            if (curr[3] == s) continue;
            for (int[] dir : DIR) {
                int nextX = curr[1] + dir[0];
                int nextY = curr[2] + dir[1];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
                if (isVisited[nextX][nextY]) continue;
                isVisited[nextX][nextY] = true;
                queue.offer(new int[]{virus[nextX][nextY], nextX, nextY, curr[3] + 1});
            }
        }
        return answer;
    }
}
