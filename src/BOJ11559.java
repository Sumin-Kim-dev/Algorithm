import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ11559 {
    final int FIELD_X = 12, FIELD_Y = 6, BOMB = 4;
    char[][] field = new char[FIELD_X][FIELD_Y];
    boolean[][] isChecked = new boolean[FIELD_X][FIELD_Y];
    Stack<Queue<int[]>> bomb = new Stack<>();

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < FIELD_X; i++) {
            field[i] = br.readLine().toCharArray();
        }
    }

    boolean bfs(int startX, int startY) {
        if (field[startX][startY] == '.') return false;
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> currBomb = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        currBomb.add(new int[]{startX, startY});
        isChecked[startX][startY] = true;
        int nextX, nextY;
        char currPuyo;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            currPuyo = field[curr[0]][curr[1]];
            for (Direction direction : Direction.values()) {
                nextX = curr[0] + direction.getX();
                nextY = curr[1] + direction.getY();
                if (!isValid(nextX, nextY)) continue;
                if (isChecked[nextX][nextY]) continue;
                if (field[nextX][nextY] == currPuyo) {
                    queue.add(new int[]{nextX, nextY});
                    currBomb.add(new int[]{nextX, nextY});
                    isChecked[nextX][nextY] = true;
                }
            }
        }
        if (currBomb.size() >= BOMB) {
            bomb.push(currBomb);
            return true;
        }
        return false;
    }

    boolean explosion() {
        boolean ongoing = false;
        for (int j = 0; j < FIELD_Y; j++) {
            for (int i = FIELD_X - 1; i >= 0; i--) {
                if (field[i][j] == '.') break;
                if (isChecked[i][j]) continue;
                ongoing |= bfs(i, j);
            }
        }
        if (!ongoing) return false;
        Queue<int[]> currBomb;
        while (!bomb.isEmpty()) {
            currBomb = bomb.pop();
            while (!currBomb.isEmpty()) {
                int[] curr = currBomb.poll();
                field[curr[0]][curr[1]] = '.';
            }
        }
        down();
        return true;
    }

    void down() {
        Queue<Character> queue;
        for (int j = 0; j < FIELD_Y; j++) {
            queue = new LinkedList<>();
            for (int i = FIELD_X - 1; i >= 0; i--) {
                if (field[i][j] != '.') queue.offer(field[i][j]);
            }
            for (int i = FIELD_X - 1; i >= 0; i--) {
                if (!queue.isEmpty()) field[i][j] = queue.poll();
                else field[i][j] = '.';
            }
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && x < FIELD_X && y >= 0 && y < FIELD_Y;
    }

    void solution() throws IOException {
        input();
        int count = 0;
        while (explosion()) {
            count++;
            isChecked = new boolean[FIELD_X][FIELD_Y];
            bomb = new Stack<>();
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ11559().solution();
    }

    enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        private final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
