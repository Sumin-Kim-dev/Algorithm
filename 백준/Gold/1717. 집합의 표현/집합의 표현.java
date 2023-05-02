import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

    int findSet(int a) {
        Node curr = set[a];
        while (curr != curr.parent) {
            curr = curr.parent;
        }
        return curr.n;
    }

    String isInSameSet(int a, int b) {
        int aSet = findSet(a);
        int bSet = findSet(b);
        if (aSet == bSet) return "YES";
        return "NO";
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        set = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            set[i] = new Node(i);
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operator == 0) union(a, b);
            if (operator == 1) sb.append(isInSameSet(a, b)).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}