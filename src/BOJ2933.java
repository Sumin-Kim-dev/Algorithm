import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ2933 {
    int r, c;
    char[][] cave;
    Queue<Integer> heights = new LinkedList<>();
    int order = -1;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cave = new char[r][];
        String row;
        for (int i = 0; i < r; i++) {
            row = br.readLine();
            cave[i] = row.toCharArray();
        }
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights.add(r - Integer.parseInt(st.nextToken()));
        }
    }

    void run(int h) {
        order *= -1;
        int curr = 0;
        if (order == -1) curr = c - 1;
        for (int i = 0; i < c; i++) {
            if (cave[h][curr + order * i] == 'x') {
                cave[h][curr + order * i] = '.';
                break;
            }
        }
        down();
    }

    void down() {
        ArrayList<Integer>[] airCluster = airCluster();
        int maxDown = Integer.MAX_VALUE;
        int currDown;
        int[] maxCol = new int[c];
        for (int j = 0; j < c; j++) {
            if (airCluster[j].isEmpty()) continue;
            for (int curr : airCluster[j]) {
                currDown = 0;
                while (true) {
                    if (curr + currDown + 1 >= r) break;
                    if (cave[curr + currDown + 1][j] == 'x'
                            && !airCluster[j].contains(curr + currDown + 1)) break;
                    currDown++;
                }
                if (currDown < maxDown)
                    maxDown = currDown;
                maxCol[j] = Math.max(maxCol[j], curr);
            }
        }
        for (int j = 0; j < c; j++) {
            if (airCluster[j].isEmpty()) continue;
            for (int curr : airCluster[j]) {
                cave[curr + maxDown][j] = cave[curr][j];
                cave[curr][j] = '.';
            }
        }
    }

    boolean[][] isVisited;

    ArrayList<Integer>[] airCluster() {
        isVisited = new boolean[r][c];
        for (int i = 0; i < c; i++) {
            if (cave[r - 1][i] == '.') continue;
            if (isVisited[r - 1][i]) continue;
            bfs(r - 1, i);
        }
        ArrayList<Integer>[] airCluster = new ArrayList[c];
        for (int j = 0; j < c; j++) {
            airCluster[j] = new ArrayList<>();
            for (int i = r - 1; i >= 0; i--) { // 아래에서부터 채움
                if (cave[i][j] == 'x' && !isVisited[i][j]) {
                    airCluster[j].add(i);
                }
            }
        }
        return airCluster;
    }

    void bfs(int i, int j) {
        Queue<int[]> bfsQ = new LinkedList<>();
        bfsQ.offer(new int[]{i, j});
        while (!bfsQ.isEmpty()) {
            int[] curr = bfsQ.poll();
            for (Direction direction : Direction.values()) {
                int[] next = new int[2];
                next[0] = curr[0] + direction.getDir()[0];
                next[1] = curr[1] + direction.getDir()[1];
                if (!isValid(next)) continue;
                if (cave[next[0]][next[1]] == '.') continue;
                if (isVisited[next[0]][next[1]]) continue;
                isVisited[next[0]][next[1]] = true;
                bfsQ.add(next);
            }
        }
    }

    boolean isValid(int[] position) {
        return position[0] >= 0 && position[0] < r && position[1] >= 0 && position[1] < c;
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(cave[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        while (!heights.isEmpty()) {
            run(heights.poll());
        }
        print();
    }

    enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        private final int[] dir;

        Direction(int i, int j) {
            this.dir = new int[]{i, j};
        }

        public int[] getDir() {
            return dir;
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ2933().solution();
    }
}
