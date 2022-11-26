import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427 {
    final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int h, w;
    int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            new BOJ5427().io(br);
        }
    }

    void io(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        int startI = 0;
        int startJ = 0;
        for (int i = 0; i < h; i++) {
            String row = br.readLine();
            for (int j = 0; j < w; j++) {
                if (row.charAt(j) == '#') map[i][j] = 0;
                else if (row.charAt(j) == '*') map[i][j] = 1;
                else map[i][j] = -1;
                if (row.charAt(j) == '@') {
                    startI = i;
                    startJ = j;
                }
            }
        }
        fire();
        System.out.println(solution(startI, startJ));
    }

    void fire() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1) queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1]};
                if (next[0] < 0 || next[0] >= h || next[1] < 0 || next[1] >= w) continue;
                if (map[next[0]][next[1]] >= 0) continue;
                map[next[0]][next[1]] = map[curr[0]][curr[1]] + 1;
                queue.offer(next);
            }
        }
    }

    String solution(int startI, int startJ) {
        if (startI == 0 || startI == h - 1 || startJ == 0 || startJ == w - 1) return "1";
        int[][] time = new int[h][w];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startI, startJ});
        time[startI][startJ] = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1]};
                if (time[next[0]][next[1]] > 0) continue;
                int currTime = time[curr[0]][curr[1]] + 1;
                if (map[next[0]][next[1]] <= currTime && map[next[0]][next[1]] >= 0) continue;
                if (next[0] == 0 || next[0] == h - 1 || next[1] == 0 || next[1] == w - 1)
                    return String.valueOf(currTime);
                queue.offer(next);
                time[next[0]][next[1]] = currTime;
            }
        }
        return "IMPOSSIBLE";
    }
}
