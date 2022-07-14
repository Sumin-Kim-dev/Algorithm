import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7869 {
    Circle[] circles;

    class Circle {
        Point point;
        double r;

        Circle(double x, double y, double r) {
            this.point = new Point(x, y);
            this.r = r;
        }
    }

    class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        circles = new Circle[2];
        for (int i = 0; i < 2; i++) {
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            double r = Double.parseDouble(st.nextToken());
            circles[i] = new Circle(x, y, r);
        }
    }

    double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    double area() {
        double dist = dist(circles[0].point, circles[1].point);
        if (dist >= circles[0].r + circles[1].r)
            return 0;
        if (dist <= circles[0].r - circles[1].r)
            return Math.PI * circles[1].r * circles[1].r;
        if (dist <= circles[1].r - circles[0].r)
            return Math.PI * circles[0].r * circles[0].r;
        // 코사인 제 2 법칙
        double[] theta = new double[2];
        for (int i = 0; i < 2; i++) {
            double cosine;
            cosine = circles[i].r * circles[i].r + dist * dist - circles[1 - i].r * circles[1 - i].r;
            cosine /= 2 * circles[i].r * dist;
            theta[i] = Math.acos(cosine);
        }
        // 활꼴의 면적 = 부채꼴의 면적 - 삼각형의 면적
        double[] segmentArea = new double[2];
        for (int i = 0; i < 2; i++) {
            segmentArea[i] = circles[i].r * circles[i].r;
            segmentArea[i] *= theta[i] - Math.sin(theta[i]);
        }
        // 삼각형의 면적
        double triangleArea = (circles[0].r + circles[1].r - dist) * circles[0].r * Math.sin(theta[0]);
        return segmentArea[0] + segmentArea[1] + triangleArea;
    }

    void solution() throws IOException {
        input();
        System.out.printf("%.3f%n", area());
    }

    public static void main(String[] args) throws IOException {
        new BOJ7869().solution();
    }
}
