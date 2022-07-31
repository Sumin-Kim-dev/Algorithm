import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
    int v;
    ArrayList<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int end, weight;

        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight > o.weight) return 1;
            if (this.weight < o.weight) return -1;
            return this.end - o.end;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adj[start].add(new Edge(end, weight));
            adj[end].add(new Edge(start, weight));
        }
    }

    int Prim() {
        int totalWeight = 0;
        boolean[] isSelected = new boolean[v];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge currEdge = pq.poll();
            int curr = currEdge.end;
            if (isSelected[curr]) continue;
            isSelected[curr] = true;
            totalWeight += currEdge.weight;
            for (Edge e : adj[curr]) {
                if (isSelected[e.end]) continue;
                pq.add(e);
            }
        }
        return totalWeight;
    }

    void solution() throws IOException {
        input();
        System.out.println(Prim());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1197().solution();
    }
}
