import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {
    final int WALL = 1;
    int l, r, c;
    int[][][] building;
    Queue<int[]> queue;
    int[] end;

    void setBuilding(BufferedReader br) throws IOException {
        String row;
        building = new int[l][r][c];
        queue = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                row = br.readLine();
                for (int k = 0; k < c; k++) {
                    switch (row.charAt(k)) {
                        case 'S' -> queue.add(new int[]{i, j, k, 0});
                        case '#' -> building[i][j][k] = WALL;
                        case 'E' -> end = new int[]{i, j, k};
                    }
                }
            }
            br.readLine();
        }
    }

    int bfs() {
        boolean[][][] isChecked = new boolean[l][r][c];
        int[] curr = queue.peek();
        isChecked[curr[0]][curr[1]][curr[2]] = true;
        int[] next = new int[3];
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (Direction direction : Direction.values()) {
                next[0] = curr[0] + direction.getI();
                next[1] = curr[1] + direction.getJ();
                next[2] = curr[2] + direction.getK();
                if (!isValid(next)) continue;
                if (isChecked[next[0]][next[1]][next[2]]) continue;
                isChecked[next[0]][next[1]][next[2]] = true;
                queue.add(new int[]{next[0], next[1], next[2], curr[3] + 1});
                if (isEnd(next)) return curr[3] + 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    void print(int ans) {
        if (ans == Integer.MAX_VALUE) System.out.println("Trapped!");
        else System.out.println("Escaped in " + ans + " minute(s).");
    }

    boolean isValid(int[] index) {
        return index[0] >= 0 && index[0] < l
                && index[1] >= 0 && index[1] < r
                && index[2] >= 0 && index[2] < c
                && building[index[0]][index[1]][index[2]] == 0;
    }

    boolean isEnd(int[] next) {
        return next[0] == end[0] && next[1] == end[1] && next[2] == end[2];
    }

    enum Direction {
        EAST(0, 0, 1), WEST(0, 0, -1),
        SOUTH(0, 1, 0), NORTH(0, -1, 0),
        DOWN(1, 0, 0), UP(-1, 0, 0);

        private final int i, j, k;

        Direction(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getK() {
            return k;
        }
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (l == 0 && r == 0 && c == 0) break;
            setBuilding(br);
            print(bfs());
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ6593().solution();
    }
}
