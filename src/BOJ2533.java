import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2533 {
    int n;
    ArrayList<Integer>[] adj;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    int[][] minEarlyAdapter;

    int minEarlyAdapter() {
        int root = 0;
        int[] curr = minEarlyAdapter(root);
        return Math.min(curr[0], curr[1]);
    }

    int[] minEarlyAdapter(int index) {
        if (minEarlyAdapter[index] != null) return minEarlyAdapter[index];
        minEarlyAdapter[index] = new int[]{0, 1};
        for (int child : adj[index]) {
            if (minEarlyAdapter[child] != null) continue;
            int[] subtree = minEarlyAdapter(child);
            minEarlyAdapter[index][0] += subtree[1];
            minEarlyAdapter[index][1] += Math.min(subtree[0], subtree[1]);
        }
        return minEarlyAdapter[index];
    }

    void solution() throws IOException {
        input();
        minEarlyAdapter = new int[n][];
        System.out.println(minEarlyAdapter());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2533().solution();
    }
}
