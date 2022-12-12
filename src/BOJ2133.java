import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(new BOJ2133().solution(n));
    }

    int solution(int n) {
        if ((n & 1) != 0) return 0;
        int[] a = new int[n / 2 + 1];
        a[0] = 1;
        a[1] = 4;
        for (int i = 2; i <= n / 2; i++) {
            a[i] = 4 * a[i - 1] - a[i - 2];
        }
        return a[n / 2] - a[n / 2 - 1];
    }
}
