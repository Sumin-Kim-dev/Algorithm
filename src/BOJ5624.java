import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5624 {
    static final int MAX = 100000;
    int n;
    int[] a;
    boolean[] sum = new boolean[4 * MAX + 1];

    public static void main(String[] args) throws IOException {
        new BOJ5624().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int good = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                sum[a[j] + a[i - 1] + 2 * MAX] = true;
            }
            for (int j = 0; j < i; j++) {
                if (sum[a[i] - a[j] + 2 * MAX]) {
                    good++;
                    break;
                }
            }
        }
        return good;
    }
}
