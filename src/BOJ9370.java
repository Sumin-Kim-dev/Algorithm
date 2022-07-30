import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9370 {
    int n, s, g, h;
    int[][] adj;
    int[] x;

    void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()) - 1;
        g = Integer.parseInt(st.nextToken()) - 1;
        h = Integer.parseInt(st.nextToken()) - 1;
        adj = new int[n][n];
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            adj[start][end] = adj[end][start] = Integer.parseInt(st.nextToken());
        }
        x = new int[t];
        for(int i = 0; i < t; i++) {
            x[i] = Integer.parseInt(br.readLine()) - 1;
        }
        Arrays.sort(x);
    }

    int[] Dijkstra(int start) {
        final int MAX = 200000000;
        int[] minDist = new int[n];
        class City implements Comparable<City> {
            final int city, dist;
            City(int city, int dist) {
                this.city = city;
                this.dist = dist;
            }

            @Override
            public int compareTo(City o) {
                return this.dist - o.dist;
            }
        }
        PriorityQueue<City> pq = new PriorityQueue<>();
        Arrays.fill(minDist, MAX);
        minDist[start] = 0;
        pq.add(new City(start, 0));
        while(!pq.isEmpty()) {
            City curr = pq.poll();
            if(minDist[curr.city] < curr.dist)
                continue;
            for(int i = 0; i < n; i++) {
                if(adj[curr.city][i] == 0) continue;
                if(adj[curr.city][i] + curr.dist < minDist[i]) {
                    minDist[i] = curr.dist + adj[curr.city][i];
                    pq.add(new City(i, minDist[i]));
                }
            }
        }
        return minDist;
    }

    void solve() {
        StringBuilder sb = new StringBuilder();
        int[] distFromS = Dijkstra(s);
        int[] distFromG = Dijkstra(g);
        int[] distFromH = Dijkstra(h);
        int distSG = distFromS[g];
        int distSH = distFromS[h];
        for(int curr : x) {
            if(distFromS[curr] - adj[g][h] == Math.min(distSG + distFromH[curr], distSH + distFromG[curr]))
                sb.append(curr + 1).append(' ');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            input(br);
            solve();
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ9370().solution();
    }
}
