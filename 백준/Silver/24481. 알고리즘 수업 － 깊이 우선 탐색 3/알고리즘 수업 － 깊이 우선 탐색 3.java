import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(adj[i]);
        }
        int[] depth = new int[n];
        Arrays.fill(depth, -1);
        dfs(r, adj, depth, 0);
        StringBuilder sb = new StringBuilder();
        for (int d : depth) {
            sb.append(d).append("\n");
        }
        System.out.print(sb);
    }
    
    static void dfs(int r, List<Integer>[] adj, int[] depth, int d) {
        depth[r] = d;
        for (int next: adj[r]) {
            if (depth[next] != -1) continue;
            dfs(next, adj, depth, d + 1);
        }
    }
}