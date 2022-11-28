import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15681 {
    int n;
    List<Integer>[] adj;
    int[] parents;
    int[] answer;

    public static void main(String[] args) throws IOException {
        new BOJ15681().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
        parents = new int[n];
        Arrays.fill(parents, -1);
        answer = new int[n];
        makeTree(r - 1);
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            sb.append(answer[Integer.parseInt(br.readLine()) - 1]).append('\n');
        }
        System.out.println(sb);
    }

    void makeTree(int r) {
        answer[r] = 1;
        for (int child : adj[r]) {
            if (child == parents[r]) continue;
            parents[child] = r;
            makeTree(child);
            answer[r] += answer[child];
        }
    }
}
