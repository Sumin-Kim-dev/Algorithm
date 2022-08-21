import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12912 {
    int n;
    ArrayList<Node>[] graph;
    Edge[] edges;
    long[] diameter;

    static class Edge {
        int from, to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public boolean equals(int from, int to) {
            return (from == this.from && to == this.to) || (from == this.to && to == this.from);
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        edges = new Edge[n - 1];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        diameter = new long[n];
        StringTokenizer st;
        int from, to, cost;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
            edges[i] = new Edge(from, to, cost);
        }
    }

    int edgeNo;

    void maxDist(int start) {
        int size = graph[start].size();
        for (int i = 0; i < size; i++) {
            Node curr = graph[start].get(i);
            if (edges[edgeNo].equals(start, curr.node)) continue;
            if (diameter[curr.node] != -1) continue;
            diameter[curr.node] = diameter[start] + curr.cost;
            maxDist(curr.node);
        }
    }

    static class Node {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    Node max(int start) {
        int index = 0;
        long max = -1;
        Arrays.fill(diameter, -1);
        diameter[start] = 0;
        maxDist(start);
        for (int i = 0; i < diameter.length; i++) {
            if (diameter[i] > max) {
                max = diameter[i];
                index = i;
            }
        }
        return new Node(index, max);
    }

    long diameter(int start) {
        int first = max(start).node;
        return max(first).cost;
    }

    long diameter() {
        return diameter(edges[edgeNo].from) + diameter(edges[edgeNo].to) + edges[edgeNo].cost;
    }

    void solution() throws IOException {
        input();
        long diameter = 0;
        for (edgeNo = 0; edgeNo < n - 1; edgeNo++) {
            diameter = Math.max(diameter, diameter());
        }
        System.out.println(diameter);
    }

    public static void main(String[] args) throws IOException {
        new BOJ12912().solution();
    }
}
