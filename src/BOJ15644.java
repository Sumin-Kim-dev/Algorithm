import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;

public class BOJ15644 {
    final int FAIL = -1, ONGOING = 0, SUCCESS = 1;
    int n, m;
    char[][] board;
    int[] hole;

    static class Board {
        int[] red = new int[2];
        int[] blue = new int[2];
        int status, dist;
        String path;

        public Board(int[] red, int[] blue, int status, int dist, String path) {
            this.red = red.clone();
            this.blue = blue.clone();
            this.status = status;
            this.dist = dist;
            this.path = path;
        }

        public Board(int status, int dist, String path) {
            this.status = status;
            this.dist = dist;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Board board = (Board) o;
            return Arrays.equals(red, board.red) && Arrays.equals(blue, board.blue);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(red);
            result = 31 * result + Arrays.hashCode(blue);
            return result;
        }
    }

    Board initial;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        int[] red = new int[2];
        int[] blue = new int[2];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    board[i][j] = '.';
                    red[0] = i;
                    red[1] = j;
                }
                if (board[i][j] == 'B') {
                    board[i][j] = '.';
                    blue[0] = i;
                    blue[1] = j;
                }
                if (board[i][j] == 'O') hole = new int[]{i, j};
            }
        }

        initial = new Board(red, blue, ONGOING, 0, "");
    }

    Board moveUpDown(Board curr, int dir) {
        int nextRed = curr.red[0];
        int nextBlue = curr.blue[0];
        char c = dir > 0 ? 'D' : 'U';
        while (nextBlue > 0 && nextBlue < n) {
            char next = board[nextBlue + dir][curr.blue[1]];
            if (next == 'O') {
                return new Board(FAIL, curr.dist + 1, "");
            }
            if (next == '#') break;
            nextBlue += dir;
        }
        while (nextRed > 0 && nextRed < n) {
            char next = board[nextRed + dir][curr.red[1]];
            if (next == 'O') {
                return new Board(SUCCESS, curr.dist + 1, curr.path + c);
            }
            if (next == '#') break;
            nextRed += dir;
        }
        if (curr.red[1] == curr.blue[1] && nextRed == nextBlue) {
            if ((curr.red[0] - curr.blue[0]) * dir > 0) nextBlue -= dir;
            else nextRed -= dir;
        }
        int[] red = {nextRed, curr.red[1]};
        int[] blue = {nextBlue, curr.blue[1]};
        return new Board(red, blue, ONGOING, curr.dist + 1, curr.path + c);
    }

    Board moveLeftRight(Board curr, int dir) {
        int nextRed = curr.red[1];
        int nextBlue = curr.blue[1];
        char c = dir > 0 ? 'R' : 'L';
        while (nextBlue > 0 && nextBlue < m) {
            char next = board[curr.blue[0]][nextBlue + dir];
            if (next == 'O') {
                return new Board(FAIL, curr.dist + 1, "");
            }
            if (next == '#') break;
            else nextBlue += dir;
        }
        while (nextRed > 0 && nextRed < m) {
            char next = board[curr.red[0]][nextRed + dir];
            if (next == 'O') {
                return new Board(SUCCESS, curr.dist + 1, curr.path + c);
            }
            if (next == '#') break;
            nextRed += dir;
        }
        if (curr.red[0] == curr.blue[0] && nextRed == nextBlue) {
            if ((curr.red[1] - curr.blue[1]) * dir > 0) nextBlue -= dir;
            else nextRed -= dir;
        }
        int[] red = {curr.red[0], nextRed};
        int[] blue = {curr.blue[0], nextBlue};
        return new Board(red, blue, ONGOING, curr.dist + 1, curr.path + c);
    }

    void bfs() {
        Queue<Board> boardQueue = new LinkedList<>();
        boardQueue.offer(initial);
        HashSet<Board> isVisited = new HashSet<>();
        isVisited.add(initial);
        while (!boardQueue.isEmpty()) {
            Board curr = boardQueue.poll();
            if (curr.dist == 10) break;
            Board[] move = {moveUpDown(curr, -1), moveUpDown(curr, 1),
                    moveLeftRight(curr, -1), moveLeftRight(curr, 1)};
            for (int i = 0; i < 4; i++) {
                if (move[i].status == SUCCESS) {
                    System.out.println(move[i].dist);
                    System.out.println(move[i].path);
                    return;
                } else if (move[i].status == ONGOING && !isVisited.contains(move[i])) {
                    isVisited.add(move[i]);
                    boardQueue.offer(move[i]);
                }
            }
        }
        System.out.println(-1);
    }

    void solution() throws IOException {
        input();
        bfs();
    }

    public static void main(String[] args) throws IOException {
        new BOJ15644().solution();
    }
}
