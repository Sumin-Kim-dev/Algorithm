import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16197 {
    int n, m;
    int[][] board;
    int[][] coins = new int[2][];

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        String row;
        int count = 0;
        for (int i = 0; i < n; i++) {
            row = br.readLine();
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == '#') board[i][j] = 1;
                if (row.charAt(j) == 'o') {
                    coins[count++] = new int[]{i, j};
                }
            }
        }
    }

    static class Coins {
        int[][] coins;
        int button;

        public Coins(int[][] coins, int button) {
            this.coins = coins.clone();
            this.button = button;
        }
    }

    int bfs() {
        Queue<Coins> queue = new LinkedList<>();
        queue.add(new Coins(coins, 0));
        int[][] nextCoins = new int[2][];
        int state;
        while (!queue.isEmpty()) {
            Coins currCoins = queue.poll();
            if (currCoins.button >= 10) continue;
            for (Direction direction : Direction.values()) {
                nextCoins[0] = move(currCoins.coins[0], direction);
                nextCoins[1] = move(currCoins.coins[1], direction);
                state = nextCoins[0].length + nextCoins[1].length;
                if (state == 0) continue;
                if (state == 2) return currCoins.button + 1;
                queue.add(new Coins(nextCoins, currCoins.button + 1));
            }
        }
        return -1;
    }

    int[] move(int[] currCoin, Direction direction) {
        int nextI = currCoin[0] + direction.getI();
        int nextJ = currCoin[1] + direction.getJ();
        if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) return new int[0];
        if (board[nextI][nextJ] == 1) return currCoin;
        return new int[]{nextI, nextJ};
    }

    void solution() throws IOException {
        input();
        System.out.println(bfs());
    }

    public static void main(String[] args) throws IOException {
        new BOJ16197().solution();
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
