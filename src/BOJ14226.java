import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        System.out.println(bfs(s));
    }

    static int bfs(int s) {
        if (s == 1) return 0;
        int[][] minTime = new int[2 * s + 1][s];
        minTime[1][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            if (curr[0] < s && minTime[curr[0]][curr[0]] == 0) {
                minTime[curr[0]][curr[0]] = minTime[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{curr[0], curr[0]});
            }
            if (curr[0] < s && minTime[curr[0] + curr[1]][curr[1]] == 0) {
                if (curr[0] + curr[1] == s) return minTime[curr[0]][curr[1]];
                minTime[curr[0] + curr[1]][curr[1]] = minTime[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{curr[0] + curr[1], curr[1]});
            }
            if (curr[0] > 0 && minTime[curr[0] - 1][curr[1]] == 0) {
                if (curr[0] - 1 == s) return minTime[curr[0]][curr[1]];
                minTime[curr[0] - 1][curr[1]] = minTime[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{curr[0] - 1, curr[1]});
            }
        }
        return -1;
    }
}
