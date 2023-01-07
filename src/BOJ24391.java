import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ24391 {
    int[] set;

    public static void main(String[] args) throws IOException {
        new BOJ24391().solution();
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        set = new int[n];
        Arrays.fill(set, -1);
        int m = Integer.parseInt(st.nextToken());
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }
        st = new StringTokenizer(br.readLine());
        int count = 0;
        int curr = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 1; i < n; i++) {
            int next = Integer.parseInt(st.nextToken()) - 1;
            if (findSet(curr) != findSet(next)) count++;
            curr = next;
        }
        System.out.println(count);
    }

    void union(int a, int b) {
        int setA = findSet(a);
        int setB = findSet(b);
        if (setA == setB) return;
        set[setB] = setA;
    }

    int findSet(int a) {
        if (set[a] < 0) return a;
        return set[a] = findSet(set[a]);
    }
}
