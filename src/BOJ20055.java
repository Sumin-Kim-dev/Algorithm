import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    int n, k;
    int start = 0;
    int[] a;
    boolean[] robot;
    int zero = 0;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[2 * n];
        robot = new boolean[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            if (a[i] == 0) zero++;
        }
        System.out.println(solution());
    }

    int solution() {
        int step = 0;
        while (true) {
            step++;
            if (!step()) break;
        }
        return step;
    }

    boolean step() {
        start = (start + 2 * n - 1) % (2 * n);
        int end = (start + n - 1) % (2 * n);
        robot[end] = false;
        for (int i = end + 2 * n - 1; i >= end; i--) {
            int curr = i % (2 * n);
            if (!robot[curr]) continue;
            int next = (curr + 1) % (2 * n);
            if (!robot[next] && a[next] > 0) {
                robot[curr] = false;
                robot[next] = true;
                a[next]--;
                if (a[next] == 0) zero++;
                if (next == end) {
                    robot[next] = false;
                }
            }
        }
        if (a[start] > 0) {
            robot[start] = true;
            a[start]--;
            if (a[start] == 0) zero++;
        }
        return zero < k;
    }

    public static void main(String[] args) throws IOException {
        new BOJ20055().io();
    }
}
