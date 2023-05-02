import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 10_000_000;
    int n;
    ArrayList<Edge> time;

    static class Edge {
        int start, end, time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

    void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        time = new ArrayList<>();
        setRoads(m, br);
        setWormHoles(w, br);
        for (int i = 1; i <= n; i++)
            time.add(new Edge(0, i, 0));
    }

    void setRoads(int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        int s, e, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            time.add(new Edge(s, e, t));
            time.add(new Edge(e, s, t));
        }
    }

    void setWormHoles(int w, BufferedReader br) throws IOException {
        StringTokenizer st;
        int s, e, t;
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            t = -Integer.parseInt(st.nextToken());
            time.add(new Edge(s, e, t));
        }
    }

    boolean BellmanFord() {
        int[] minTime = new int[n + 1];
        Arrays.fill(minTime, INF);
        minTime[0] = 0;
        for (int k = 1; k <= minTime.length; k++) {
            for (Edge e : time) {
                if (minTime[e.start] == INF) continue;
                if (minTime[e.start] + e.time < minTime[e.end]) {
                    if (k == minTime.length) return true;
                    minTime[e.end] = minTime[e.start] + e.time;
                }
            }
        }
        return false;
    }

    String answer() {
        if (BellmanFord()) return "YES";
        return "NO";
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            input(br);
            System.out.println(answer());
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
