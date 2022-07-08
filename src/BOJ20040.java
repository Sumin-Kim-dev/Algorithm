import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20040 {
    int n, m;
    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        Arrays.fill(parent, -1);
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (isInSameSet(v1, v2)) {
                ans = i;
                break;
            }
            union(v1, v2);
        }
        System.out.println(ans);
    }

    int findSet(int v) {
        int s = parent[v];
        if (s < 0) return v;
        return findSet(s);
    }

    boolean isInSameSet(int v1, int v2) {
        return findSet(v1) == findSet(v2);
    }

    void union(int v1, int v2) {
        if (isInSameSet(v1, v2)) return;
        int s1 = findSet(v1);
        int s2 = findSet(v2);
        // s1이 더 큰 집합인 경우
        if (-parent[s1] >= -parent[s2]) {
            parent[s1] = parent[s1] + parent[s2];
            parent[s2] = s1;
        } else { // s2가 더 큰 집합인 경우
            parent[s2] = parent[s1] + parent[s2];
            parent[s1] = s2;
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ20040().solution();
    }
}
