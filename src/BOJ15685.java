import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;

public class BOJ15685 {
    int n;
    int[][] cmd;
    boolean[][] square = new boolean[101][101];

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] cmd = new String[n];
        for (int i = 0; i < n; i++) {
            cmd[i] = br.readLine();
        }
        System.out.println(solution(n, cmd));
    }

    int solution(int n, String[] cmd) {
        this.n = n;
        this.cmd = new int[n][4];
        for (int i = 0; i < n; i++) {
            String[] curr = cmd[i].split(" ");
            for (int j = 0; j < 4; j++) {
                this.cmd[i][j] = Integer.parseInt(curr[j]);
            }
            curve(this.cmd[i]);
        }
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (square[i][j] && square[i + 1][j] && square[i][j + 1] && square[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        return count;
    }

    void curve(int[] cmd) {
        final int[][] DIRECTION = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        int x = cmd[0];
        int y = cmd[1];
        int[] dir = DIRECTION[cmd[2]];
        int g = cmd[3];
        int endX = cmd[0] + dir[0];
        int endY = cmd[1] + dir[1];
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(x, y));
        if (g == 0) points.add(new Point(endX, endY));
        for (int i = 0; i < g; i++) {
            HashSet<Point> nextPoints = new HashSet<>();
            nextPoints.add(new Point(endX, endY));
            for (Point point : points) {
                nextPoints.add(point);
                nextPoints.add(new Point(endX + endY - point.y, endY - endX + point.x));
            }
            int temp = endX;
            endX = endX + endY - y;
            endY = endY - temp + x;
            points = nextPoints;
        }
        for (Point point : points) {
            square[point.x][point.y] = true;
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ15685().io();
    }
}
