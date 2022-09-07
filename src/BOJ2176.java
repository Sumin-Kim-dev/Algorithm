import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2176 {
    final int s = 0, t = 1, INF = 100_000_000;
    int n;
    ArrayList<int[]>[] adj;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int start, end, weight;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            adj[start].add(new int[]{end, weight});
            adj[end].add(new int[]{start, weight});
        }
    }

    int[] minDist;

    void Dijkstra(int start) {
        minDist = new int[n];
        Arrays.fill(minDist, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0});
        minDist[start] = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (minDist[curr[0]] < curr[1]) continue;
            for (int[] next : adj[curr[0]]) {
                if (curr[1] + next[1] < minDist[next[0]]) {
                    minDist[next[0]] = curr[1] + next[1];
                    pq.add(new int[]{next[0], minDist[next[0]]});
                }
            }
        }
    }

    int[] count;
    boolean[] isVisited;

    int dp(int start) {
        if (isVisited[start]) return count[start];
        isVisited[start] = true;
        if (start == t) return count[start] = 1;
        for (int[] next : adj[start]) {
            if (minDist[start] <= minDist[next[0]]) continue;
            count[start] += dp(next[0]);
        }
        return count[start];
    }

    void solution() throws IOException {
        input();
        Dijkstra(t);
        count = new int[n];
        isVisited = new boolean[n];
        System.out.println(dp(s));
    }

    public static void main(String[] args) throws IOException {
        new BOJ2176().solution();
    }
}
