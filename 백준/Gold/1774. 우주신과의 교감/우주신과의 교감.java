import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    final double INF = 2_000_000_000;
    int n;
    long[][] points;
    double[][] dist;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        points = new long[n][2];
        dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
            for (int j = 0; j < i; j++) {
                dist[i][j] = dist[j][i] = dist(points[i], points[j]);
            }
        }
        int cnt1, cnt2;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            cnt1 = Integer.parseInt(st.nextToken()) - 1;
            cnt2 = Integer.parseInt(st.nextToken()) - 1;
            dist[cnt1][cnt2] = dist[cnt2][cnt1] = 0;
        }
    }

    double dist(long[] point1, long[] point2) {
        return Math.sqrt((point1[0] - point2[0]) * (point1[0] - point2[0]) +
                (point1[1] - point2[1]) * (point1[1] - point2[1]));
    }

    double Prim() {
        class God {
            final int godNo;
            final double minDist;

            public God(int godNo, double minDist) {
                this.godNo = godNo;
                this.minDist = minDist;
            }
        }
        double[] minDist = new double[n];
        boolean[] checked = new boolean[n];
        Arrays.fill(minDist, INF);

        minDist[0] = 0;
        PriorityQueue<God> gods = new PriorityQueue<>(Comparator.comparingDouble(g -> g.minDist));
        double totalLength = 0;
        gods.add(new God(0, 0));
        while (!gods.isEmpty()) {
            God curr = gods.poll();
            if (checked[curr.godNo]) continue;
            checked[curr.godNo] = true;
            minDist[curr.godNo] = 0;
            totalLength += curr.minDist;
            for (int i = 0; i < n; i++) {
                if (checked[i]) continue;
                if (minDist[curr.godNo] + dist[curr.godNo][i] < minDist[i]) {
                    minDist[i] = minDist[curr.godNo] + dist[curr.godNo][i];
                    gods.add(new God(i, minDist[i]));
                }
            }
        }
        return totalLength;
    }

    void solution() throws IOException {
        input();
        System.out.printf("%.2f", Prim());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
