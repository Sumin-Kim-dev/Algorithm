import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1774 {
    int n, m;
    long[][] points;
    int[] sets;
    PriorityQueue<Path> paths = new PriorityQueue<>(Comparator.comparingDouble(o -> o.dist));

    static class Path {
        int start, end;
        double dist;

        public Path(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        points = new long[n][2];
        sets = new int[n];
        Arrays.fill(sets, -1);
        setPoints(br);
        setConnectedGods(br);
    }

    void setPoints(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
            for (int j = 0; j < i; j++) {
                paths.add(new Path(i, j, dist(points[i], points[j])));
            }
        }
    }

    void setConnectedGods(BufferedReader br) throws IOException {
        StringTokenizer st;
        int cnt1, cnt2;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            cnt1 = Integer.parseInt(st.nextToken()) - 1;
            cnt2 = Integer.parseInt(st.nextToken()) - 1;
            union(cnt1, cnt2);
        }
    }

    int findSet(int i) {
        if (sets[i] < 0) return i;
        return sets[i] = findSet(sets[i]);
    }

    boolean union(int i, int j) {
        int setI = findSet(i);
        int setJ = findSet(j);
        if (setI == setJ) return false;
        if (-sets[setI] < -sets[setJ]) {
            int temp = setI;
            setI = setJ;
            setJ = temp;
        }
        sets[setI] += sets[setJ];
        sets[setJ] = setI;
        return true;
    }

    double dist(long[] point1, long[] point2) {
        return Math.sqrt((point1[0] - point2[0]) * (point1[0] - point2[0]) +
                (point1[1] - point2[1]) * (point1[1] - point2[1]));
    }

    double Kruskal() {
        double minDist = 0;
        int count = 0;
        while (!paths.isEmpty() && count < n) {
            Path curr = paths.poll();
            if (union(curr.start, curr.end)) {
                count++;
                minDist += curr.dist;
            }
        }
        return minDist;
    }

    void solution() throws IOException {
        input();
        System.out.printf("%.2f", Kruskal());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1774().solution();
    }
}
