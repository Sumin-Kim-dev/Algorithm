import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2631 {
    int n;
    int[] students;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        students = new int[n];
        for (int i = 0; i < n; i++) {
            students[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution());
    }

    int solution() {
        int lis = 0;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n;
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                if (f[mid] < students[i]) start = mid;
                else end = mid;
            }
            f[end] = students[i];
            if (end > lis) lis = end;
        }
        return n - lis;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2631().io();
    }
}
