import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {
    int n, m;
    int[][] adj;
    int[] path;
    Node[] set;

    class Node {
        int n, level;
        Node parent;

        Node(int n) {
            this.n = n;
            this.level = 0;
            setParent(this);
        }

        void setParent(Node parent) {
            this.parent = parent;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        set = new Node[n];
        path = new int[m];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            set[i] = new Node(i);
            for (int j = 0; j < i; j++) {
                int adj = Integer.parseInt(st.nextToken());
                if(adj == 0) continue;
                if(findSet(i) == findSet(j)) continue;
                union(i, j);
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            path[i] = Integer.parseInt(st.nextToken()) - 1;
    }

    int findSet(int a) {
        Node curr = set[a];
        while (curr != curr.parent) {
            curr = curr.parent;
        }
        return curr.n;
    }

    String isAble(int[] path) {
        int set = findSet(path[0]);
        for(int i = 1; i < path.length; i++) {
            if(set != findSet(path[i])) return "NO";
        }
        return "YES";
    }

    void union(int a, int b) {
        int aSet = findSet(a);
        int bSet = findSet(b);
        if (aSet == bSet) return;
        int aSetLevel = set[aSet].level;
        int bSetLevel = set[bSet].level;
        if (aSetLevel >= bSetLevel) {
            set[bSet].parent = set[aSet];
            if (aSetLevel == bSetLevel) set[aSet].level++;
            return;
        }
        set[aSet].parent = set[bSet];
    }

    void solution() throws IOException {
        input();
        System.out.println(isAble(path));
    }

    public static void main(String[] args) throws IOException {
        new BOJ1976().solution();
    }
}
