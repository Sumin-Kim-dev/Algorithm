import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1967 {
    int n;
    ArrayList<Node>[] adj;

    class Node {
        int node, dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    void makeGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adj[start].add(new Node(end, weight));
            adj[end].add(new Node(start, weight));
        }
    }

    Node maxDist(int index) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[index] = 0;
        dfs(index, dist);
        int maxIndex = 0;
        int maxDist = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                maxIndex = i;
            }
        }
        return new Node(maxIndex, maxDist);
    }

    void dfs(int index, int[] dist) {
        int size = adj[index].size();
        for (int i = 0; i < size; i++) {
            Node next = adj[index].get(i);
            if (dist[next.node] == -1) {
                dist[next.node] = dist[index] + next.dist;
                dfs(next.node, dist);
            }
        }
    }

    void solution() throws IOException {
        makeGraph();
        int a = maxDist(0).node;
        int radius = maxDist(a).dist;
        System.out.println(radius);
    }

    public static void main(String[] args) throws IOException {
        new BOJ1967().solution();
    }
}
