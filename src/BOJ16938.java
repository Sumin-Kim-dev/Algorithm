import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16938 {
    int n, l, r, x;
    int[] a;
    boolean[] curr;
    int count = 0;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        curr = new boolean[n];
        backtracking(0);
        System.out.println(count);
    }

    void backtracking(int depth) {
        if (depth == n) {
            int sum = 0;
            int max = 0;
            int min = 1000000;
            for (int i = 0; i < n; i++) {
                if (!curr[i]) continue;
                sum += a[i];
                if (max < a[i]) max = a[i];
                if (min > a[i]) min = a[i];
            }
            if (sum >= l && sum <= r && max - min >= x) count++;
            return;
        }
        backtracking(depth + 1);
        curr[depth] = true;
        backtracking(depth + 1);
        curr[depth] = false;
    }

    public static void main(String[] args) throws IOException {
        new BOJ16938().io();
    }
}
