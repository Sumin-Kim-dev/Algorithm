import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1865 {
    static final int INF = 10_000_000;
    int n;
    ArrayList<Edge> time;
    boolean[] checked;

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
        checked = new boolean[n];
        setRoads(m, br);
        setWormHoles(w, br);
    }

    void setRoads(int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        int s, e, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
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
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            t = -Integer.parseInt(st.nextToken());
            time.add(new Edge(s, e, t));
        }
    }

    boolean BellmanFord(int start) {
        int[] minTime = new int[n];
        Arrays.fill(minTime, INF);
        minTime[start] = 0;
        checked[start] = true;
        for (int k = 1; k <= n; k++) {
            for (Edge e : time) {
                if (minTime[e.start] == INF) continue;
                if (minTime[e.start] + e.time < minTime[e.end]) {
                    if (k == n) return true;
                    minTime[e.end] = minTime[e.start] + e.time;
                    checked[e.end] = true;
                }
            }
        }
        return false;
    }

    String answer() {
        for (int i = 0; i < n; i++) {
            if (checked[i]) continue;
            if (BellmanFord(i)) return "YES";
        }
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
        new BOJ1865().solution();
    }
}
