import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2162 {
    int n;
    Line[] lines;
    int[] parents;

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Line {
        Point p1, p2;

        Line(int x1, int y1, int x2, int y2) {
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new Line[n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2);
            parents[i] = -1;
        }
    }

    boolean isCross(Line line1, Line line2) {
        int point1 = crossProduct(line1.p1, line2);
        int point2 = crossProduct(line1.p2, line2);
        int point3 = crossProduct(line2.p1, line1);
        int point4 = crossProduct(line2.p2, line1);
        if (hasDiffSign(point1, point2) && hasDiffSign(point3, point4))
            return true;
        if (point1 == 0 && isInLine(line1.p1, line2))
            return true;
        if (point2 == 0 && isInLine(line1.p2, line2))
            return true;
        if (point3 == 0 && isInLine(line2.p1, line1))
            return true;
        if (point4 == 0 && isInLine(line2.p2, line1))
            return true;
        return false;
    }

    boolean isInLine(Point p, Line line) {
        return crossProduct(p, line) == 0 && isIn(p.x, line.p1.x, line.p2.x) && isIn(p.y, line.p1.y, line.p2.y);
    }

    boolean isIn(int p, int a1, int a2) {
        return (a1 <= p && p <= a2) || (a2 <= p && p <= a1);
    }

    boolean hasDiffSign(int x, int y) {
        return (x > 0 && y < 0) || (x < 0 && y > 0);
    }

    int crossProduct(Point p, Line line) {
        return (p.x - line.p1.x) * (p.y - line.p2.y) - (p.x - line.p2.x) * (p.y - line.p1.y);
    }

    int findSet(int i) {
        if (parents[i] < 0) return i;
        return findSet(parents[i]);
    }

    boolean isInSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    void union(int i, int j) {
        if (isInSameSet(i, j)) return;
        int setI = findSet(i);
        int setJ = findSet(j);
        if (-parents[setI] >= -parents[setJ]) {
            parents[setI] += parents[setJ];
            parents[setJ] = setI;
        }
        if (-parents[setI] < -parents[setJ]) {
            parents[setJ] += parents[setI];
            parents[setI] = setJ;
        }
    }

    void solution() throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isInSameSet(i, j))
                    continue;
                if (isCross(lines[i], lines[j]))
                    union(i, j);
            }
        }
        int groups = 0, maxSize = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] < 0) groups++;
            if (-parents[i] > maxSize) maxSize = -parents[i];
        }
        System.out.printf("%d\n%d", groups, maxSize);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2162().solution();
    }
}
