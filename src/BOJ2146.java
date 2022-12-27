import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146 {
    static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int n;
    int[][] map;
    Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        new BOJ2146().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
                if (map[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        System.out.println(solution());
    }

    int solution() {
        setMap();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int currNo = map[curr[0]][curr[1]] % 10000;
                int currLength = map[curr[0]][curr[1]] / 10000;
                for (int[] dir : DIR) {
                    int[] next = {curr[0] + dir[0], curr[1] + dir[1]};
                    if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                    if (map[next[0]][next[1]] == -1) {
                        map[next[0]][next[1]] = map[curr[0]][curr[1]] + 10000;
                        queue.offer(next);
                    }
                    if (map[next[0]][next[1]] > 0) {
                        int nextNo = map[next[0]][next[1]] % 10000;
                        int nextLength = map[next[0]][next[1]] / 10000;
                        if (currNo == nextNo) continue;
                        if (min > currLength + nextLength) min = currLength + nextLength;
                    }
                }
            }
            if (min < Integer.MAX_VALUE) return min;
        }
        return 0;
    }

    void setMap() {
        int no = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] < 0) continue;
                if (map[i][j] == 0) no = setGround(i, j, no);
            }
        }
    }

    int setGround(int i, int j, int no) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        map[i][j] = no;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                if (map[next[0]][next[1]] == 0) {
                    map[next[0]][next[1]] = no;
                    queue.offer(next);
                }
            }
        }
        return no + 1;
    }
}
