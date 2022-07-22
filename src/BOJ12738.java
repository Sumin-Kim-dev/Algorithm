import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12738 {
    static final int MIN = Integer.MIN_VALUE;
    static final int MAX = Integer.MAX_VALUE;
    int n;
    int[] a;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
    }

    int maxLis() {
        int[] f = new int[n + 1];
        int lis = 1;
        initialize(f);
        for (int i = 1; i < n; i++) {
            if(a[i] > f[lis]) {
                f[lis + 1] = a[i];
                lis++;
            }
            else {
                int start = 0;
                int end = lis;
                while(start + 1 < end) {
                    int mid = (start + end) / 2;
                    if(f[mid] < a[i]) start = mid;
                    else end = mid;
                }
                f[end] = a[i];
            }
        }
        return lis;
    }

    void initialize(int[] f) {
        f[0] = MIN;
        f[1] = a[0];
        for (int i = 2; i < f.length; i++)
            f[i] = MAX;
    }

    void solution() throws IOException {
        input();
        System.out.println(maxLis());
    }

    public static void main(String[] args) throws IOException {
        new BOJ12738().solution();
    }
}
