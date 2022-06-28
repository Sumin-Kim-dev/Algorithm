import java.io.*;
import java.util.StringTokenizer;

public class BOJ20149 {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private long[][] points = new long[4][2];
    private long[][] coeff;
    private long[] constant;

    private void input() throws IOException {
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            points[i / 2][i % 2] = Integer.parseInt(st1.nextToken());
            points[i / 2 + 2][i % 2] = Integer.parseInt(st2.nextToken());
        }
    }

    private void setLinearEq() {
        coeff = new long[][]{{points[1][1] - points[0][1], points[0][0] - points[1][0]},
                {points[3][1] - points[2][1], points[2][0] - points[3][0]}};
        constant = new long[]{points[0][0] * points[1][1] - points[1][0] * points[0][1],
                points[2][0] * points[3][1] - points[3][0] * points[2][1]};
    }

    private boolean isParallel() {
        return det(coeff) == 0;
    }

    private long[][] solveEquation() {
        long[][] ans = new long[2][2];
        ans[0][0] = det(new long[][]{{constant[0], coeff[0][1]}, {constant[1], coeff[1][1]}});
        ans[1][0] = det(new long[][]{{coeff[0][0], constant[0]}, {coeff[1][0], constant[1]}});
        ans[0][1] = ans[1][1] = det(coeff);
        return ans;
    }

    private long det(long[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private class Cross {
        int isCross;
        Point point;

        class Point {
            double x, y;

            Point(double x, double y) {
                this.x = x;
                this.y = y;
            }
        }

        Cross(boolean isCross) {
            if (isCross) this.isCross = 1;
            else this.isCross = 0;
            this.point = null;
        }

        Cross setPoint(double x, double y) {
            point = new Point(x, y);
            return this;
        }
    }

    private Cross overlap(long p0, long p1, long p2, long p3) {
        if (Math.max(p0, p1) < Math.min(p2, p3) || Math.max(p2, p3) < Math.min(p0, p1))
            return new Cross(false);
        if (Math.max(p0, p1) > Math.min(p2, p3) && Math.max(p2, p3) > Math.min(p0, p1))
            return new Cross(true);
        return new Cross(true).setPoint(0, 0);
    }

    private boolean isIn(long p0, long p1, long p) {
        return (p <= p0 && p1 <= p) || (p <= p1 && p0 <= p);
    }

    private Cross cross() {
        // 선분 하나가 사실 점인 경우
        if (points[0][0] == points[1][0] && points[0][1] == points[1][1]) {
            if (coeff[1][0] * points[0][0] + coeff[1][1] * points[0][1] == constant[1] &&
                    isIn(points[2][0], points[3][0], points[0][0])
                    && isIn(points[2][1], points[3][1], points[0][1]))
                return new Cross(true).setPoint(points[0][0], points[0][1]);
            return new Cross(false);
        }
        if (points[2][0] == points[3][0] && points[2][1] == points[3][1]) {
            if (coeff[0][0] * points[2][0] + coeff[0][1] * points[2][1] == constant[0] &&
                    isIn(points[0][0], points[1][0], points[2][0])
                    && isIn(points[0][1], points[1][1], points[2][1]))
                return new Cross(true).setPoint(points[2][0], points[2][1]);
            return new Cross(false);
        }
        // 두 직선이 평행한 경우
        if (isParallel()) {
            // 두 직선이 나란한 경우
            if (coeff[0][0] * points[2][0] + coeff[0][1] * points[2][1] != constant[0])
                return new Cross(false);
            // 두 직선이 같은 경우
            // 직선이 y축과 평행하지는 않은 경우
            Cross answer;
            if (points[0][0] != points[1][0]) {
                answer = overlap(points[0][0], points[1][0], points[2][0], points[3][0]);
                if (answer.point == null) return answer;
                // points[0]이 유일한 겹치는 점
                if (points[0][0] == points[2][0] || points[0][0] == points[3][0])
                    return answer.setPoint(points[0][0], points[0][1]);
                // points[1]이 유일한 겹치는 점
                return answer.setPoint(points[1][0], points[1][1]);
            } else { // 직선이 y축과 평행한 경우
                answer = overlap(points[0][1], points[1][1], points[2][1], points[3][1]);
                if (answer.point == null) return answer;
                // points[0]이 유일한 겹치는 점
                if (points[0][1] == points[2][1] || points[0][1] == points[3][1])
                    return answer.setPoint(points[0][0], points[0][1]);
                // points[1]이 유일한 겹치는 점
                return answer.setPoint(points[1][0], points[1][1]);
            }
        }
        // 두 직선이 평행하지 않은 경우
        long[][] cross = solveEquation();
        if (isIn(points[0][0] * cross[0][1], points[1][0] * cross[1][1], cross[0][0])
                && isIn(points[0][1] * cross[0][1], points[1][1] * cross[1][1] , cross[1][0])
                && isIn(points[2][0] * cross[0][1], points[3][0] * cross[1][1], cross[0][0])
                && isIn(points[2][1] * cross[0][1], points[3][1] * cross[1][1], cross[1][0])) {
            return new Cross(true).setPoint((double)cross[0][0] / cross[0][1], (double)cross[1][0] / cross[1][1]);
        }
        return new Cross(false);
    }

    private void print() throws IOException {
        StringBuilder sb = new StringBuilder();
        Cross cross = cross();
        sb.append(cross.isCross).append('\n');
        if (cross.point != null)
            sb.append(cross.point.x).append(' ').append(cross.point.y);
        bw.write(sb.toString());
        bw.close();
    }

    private void solution() throws IOException {
        input();
        setLinearEq();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ20149().solution();
    }
}
