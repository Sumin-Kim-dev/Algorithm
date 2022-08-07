import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {
    int n;
    double[][] points;
    double[][] dist;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        points = new double[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }
    }

    void setDist() {
        dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = dist(points[i], points[j]);
            }
        }
    }

    double Prim() {
        final double INF = 1_000_000;
        class Star {
            final int starNo;
            final double cost;

            Star(int starNo, double cost) {
                this.starNo = starNo;
                this.cost = cost;
            }
        }
        double totalCost = 0;
        double[] cost = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(cost, INF);
        PriorityQueue<Star> stars = new PriorityQueue<>(Comparator.comparingDouble(s -> s.cost));
        stars.add(new Star(0, 0));
        while (!stars.isEmpty()) {
            Star currStar = stars.poll();
            int curr = currStar.starNo;
            if (visited[curr]) continue;
            totalCost += currStar.cost;
            visited[curr] = true;
            for (int i = 0; i < n; i++) {
                if (dist[i][curr] < cost[i]) {
                    cost[i] = dist[i][curr];
                    stars.add(new Star(i, cost[i]));
                }
            }
        }
        return totalCost;
    }

    double dist(double[] point1, double[] point2) {
        return Math.sqrt((point1[0] - point2[0]) * (point1[0] - point2[0])
                + (point1[1] - point2[1]) * (point1[1] - point2[1]));
    }

    void solution() throws IOException {
        input();
        setDist();
        System.out.println(Prim());
    }

    public static void main(String[] args) throws IOException {
        new BOJ4386().solution();
    }
}
