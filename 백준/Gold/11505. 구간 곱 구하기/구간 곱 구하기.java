import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int segSize = 1;
        while (segSize <= n) {
            segSize <<= 1;
        }
        segSize <<= 1;

        int[] segTree = new int[segSize];
        initSegTree(1, 0, n - 1, nums, segTree);

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) update(1, 0, n - 1, b - 1, c, segTree);
            else sb.append(query(1, 0, n - 1, b - 1, c - 1, segTree)).append('\n');
        }
        System.out.println(sb);
    }

    private static int initSegTree(int i, int start, int end, int[] nums, int[] segTree) {
        if (start == end) {
            return segTree[i] = nums[start];
        }
        int mid = (start + end) / 2;
        return segTree[i] = (int) ((long) initSegTree(2 * i, start, mid, nums, segTree)
                * initSegTree(2 * i + 1, mid + 1, end, nums, segTree)
                % MOD);
    }

    private static int query(int ni, int ns, int ne, int s, int e, int[] segTree) {
        if (s <= ns && e >= ne) return segTree[ni];
        if (ne < s || ns > e) return 1;
        int mid = (ns + ne) / 2;
        return (int) ((long) query(2 * ni, ns, mid, s, e, segTree)
                * query(2 * ni + 1, mid + 1, ne, s, e, segTree)
                % MOD);
    }

    private static int update(int ni, int ns, int ne, int i, int num, int[] segTree) {
        if (ne < i || ns > i) return segTree[ni];
        if (ns == ne) return segTree[ni] = num;
        int mid = (ns + ne) / 2;
        return segTree[ni] = (int) ((long) update(2 * ni, ns, mid, i, num, segTree)
                * update(2 * ni + 1, mid + 1, ne, i, num, segTree)
                % MOD);
    }
}