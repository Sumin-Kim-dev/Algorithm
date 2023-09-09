import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] minSegTree = new int[2 * n];
        int[] maxSegTree = new int[2 * n];
        for (int i = 0; i < n; i++) {
            minSegTree[i + n] = maxSegTree[i + n] = Integer.parseInt(br.readLine());
        }
        initMinSegTree(n, minSegTree);
        initMaxSegTree(n, maxSegTree);

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());
            sb.append(minQuery(a + n, b + n, minSegTree)).append(' ').append(maxQuery(a + n, b + n, maxSegTree)).append('\n');
        }
        System.out.println(sb);
    }

    private static void initMinSegTree(int n, int[] segTree) {
        for (int i = n - 1; i >= 0; i--) {
            segTree[i] = Math.min(segTree[i << 1], segTree[i << 1 | 1]);
        }
    }

    private static void initMaxSegTree(int n, int[] segTree) {
        for (int i = n - 1; i >= 0; i--) {
            segTree[i] = Math.max(segTree[i << 1], segTree[i << 1 | 1]);
        }
    }

    private static int minQuery(int s, int e, int[] segTree) {
        int result = Integer.MAX_VALUE;
        while (s < e) {
            if ((s & 1) != 0) result = Math.min(result, segTree[s++]);
            if ((e & 1) != 0) result = Math.min(result, segTree[--e]);
            s >>= 1;
            e >>= 1;
        }
        return result;
    }

    private static int maxQuery(int s, int e, int[] segTree) {
        int result = Integer.MIN_VALUE;
        while (s < e) {
            if ((s & 1) != 0) result = Math.max(result, segTree[s++]);
            if ((e & 1) != 0) result = Math.max(result, segTree[--e]);
            s >>= 1;
            e >>= 1;
        }
        return result;
    }
}