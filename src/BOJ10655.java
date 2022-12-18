import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10655 {
    int n;
    int[][] points;

    public static void main(String[] args) throws IOException {
        new BOJ10655().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        points = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] dist = new int[n - 1];
        int distSum = 0;
        for (int i = 0; i < n - 1; i++) {
            dist[i] = dist(points[i], points[i + 1]);
            distSum += dist[i];
        }
        int skip = 0;
        for (int i = 1; i < n - 1; i++) {
            skip = Math.max(skip, dist[i - 1] + dist[i] - dist(points[i - 1], points[i + 1]));
        }
        return distSum - skip;
    }

    int dist(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0])
                + Math.abs(point1[1] - point2[1]);
    }
}
