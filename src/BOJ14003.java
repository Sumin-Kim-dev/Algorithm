import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14003 {
    static final int MIN = Integer.MIN_VALUE;
    static final int MAX = Integer.MAX_VALUE;
    int n;
    int[] a;

    static class LIS {
        int lisLength;
        Stack<Integer> reverseLis;

        LIS() {
            lisLength = 0;
            reverseLis = new Stack<>();
        }

        String print() {
            StringBuilder sb = new StringBuilder();
            sb.append(lisLength).append('\n');
            while (!reverseLis.isEmpty()) {
                sb.append(reverseLis.pop()).append(' ');
            }
            return sb.toString();
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
    }

    LIS lis() {
        LIS lis = new LIS();
        int[] currLisLength = currLisLength();
        int lisLastIndex = 0;
        for (int i = 0; i < n; i++) {
            if (lis.lisLength < currLisLength[i]) {
                lis.lisLength = currLisLength[i];
                lisLastIndex = i;
            }
        }
        int currLisIndex = lis.lisLength;
        for (int i = lisLastIndex; i >= 0; i--) {
            if (i == lisLastIndex || currLisIndex == currLisLength[i] && a[i] < lis.reverseLis.peek()) {
                lis.reverseLis.push(a[i]);
                currLisIndex--;
            }
            if (currLisIndex < 0) break;
        }
        return lis;
    }

    int[] currLisLength() {
        int[] f = new int[n + 1];
        int[] currLisLength = new int[n];
        int lis = 0;
        initialize(f);
        for (int i = 0; i < n; i++) {
            if (a[i] > f[lis]) {
                lis++;
                currLisLength[i] = lis;
                f[lis] = a[i];
            } else {
                int start = 0;
                int end = lis;
                while (start + 1 < end) {
                    int mid = (start + end) / 2;
                    if (f[mid] < a[i]) start = mid;
                    else end = mid;
                }
                currLisLength[i] = end;
                f[currLisLength[i]] = a[i];
            }
        }
        return currLisLength;
    }

    void initialize(int[] f) {
        f[0] = MIN;
        for (int i = 1; i < f.length; i++)
            f[i] = MAX;
    }

    void solution() throws IOException {
        input();
        System.out.println(lis().print());
    }

    public static void main(String[] args) throws IOException {
        new BOJ14003().solution();
    }
}
