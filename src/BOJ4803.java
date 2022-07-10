import java.io.*;
import java.util.StringTokenizer;

public class BOJ4803 {
    final static int CYCLE = -1000;
    int n, m;
    int[] parents;
    BufferedReader br;

    BOJ4803() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    void test() throws IOException {
        for (int i = 1; ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            setGraph(n, m);
            print(i, numOfTrees());
        }
    }

    int numOfTrees() {
        int count = 0;
        for (int i = 0; i < n; i++)
            if (parents[i] < 0 && parents[i] != CYCLE)
                count++;
        return count;
    }

    void print(int i, int numOfTrees) {
        StringBuilder sb = new StringBuilder();
        sb.append("Case ").append(i).append(": ");
        if (numOfTrees == 0)
            sb.append("No trees.");
        if (numOfTrees == 1)
            sb.append("There is one tree.");
        if(numOfTrees > 1)
            sb.append("A forest of ").append(numOfTrees).append(" trees.");
        System.out.println(sb);
    }

    void setGraph(int n, int m) throws IOException {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = -1;
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            union(start, end);
        }
    }

    void union(int start, int end) {
        int setStart = findSet(start);
        int setEnd = findSet(end);
        if (setStart == setEnd) {
            parents[setStart] = CYCLE;
            return;
        }
        if (-parents[setStart] >= -parents[setEnd]) {
            if (parents[setStart] != CYCLE)
                parents[setStart] += parents[setEnd];
            parents[setEnd] = setStart;
        } else {
            if (parents[setEnd] != CYCLE)
                parents[setEnd] += parents[setStart];
            parents[setStart] = setEnd;
        }
    }

    int findSet(int index) {
        if (parents[index] < 0)
            return index;
        return findSet(parents[index]);
    }

    void solution() throws IOException {
        test();
    }

    public static void main(String[] args) throws IOException {
        new BOJ4803().solution();
    }
}
