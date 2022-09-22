import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {
    int n, l, r;
    int[][] country;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        country = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    boolean[][] isVisited;

    boolean move() {
        isVisited = new boolean[n][n];
        int[][] next = new int[n][n];
        boolean move = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isVisited[i][j]) continue;
                move |= move(i, j, next);
            }
        }
        country = next;
        return move;
    }

    boolean move(int i, int j, int[][] next) {
        ArrayList<int[]> union = union(i, j);
        int totalPeople = 0;
        for (int[] curr : union) {
            totalPeople += country[curr[0]][curr[1]];
        }
        for (int[] curr : union) {
            next[curr[0]][curr[1]] = totalPeople / union.size();
        }
        return union.size() > 1;
    }

    ArrayList<int[]> union(int i, int j) {
        ArrayList<int[]> union = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        union.add(new int[]{i, j});
        queue.offer(new int[]{i, j, country[i][j]});
        isVisited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] currCountry = queue.poll();
            for (Direction dir : Direction.values()) {
                int nextI = currCountry[0] + dir.getI();
                int nextJ = currCountry[1] + dir.getJ();
                if (!isValid(nextI, nextJ)) continue;
                if (isVisited[nextI][nextJ]) continue;
                int diff = Math.abs(currCountry[2] - country[nextI][nextJ]);
                if (diff >= l && diff <= r) {
                    queue.offer(new int[]{nextI, nextJ, country[nextI][nextJ]});
                    isVisited[nextI][nextJ] = true;
                    union.add(new int[]{nextI, nextJ});
                }
            }
        }
        return union;
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    void solution() throws IOException {
        input();
        int count = 0;
        while (move()) {
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ16234().solution();
    }

    enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        private final int i, j;

        Direction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }
}
