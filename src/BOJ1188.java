import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1188 {
    public static void main(String[] args) throws IOException {
        new BOJ1188().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        System.out.println(solution(n, m));
    }

    int solution(int n, int m) {
        int gcd = gcd(n, m);
        return m - gcd;
    }

    int gcd(int n, int m) {
        if (n == 0) return m;
        if (n > m) return gcd(m, n);
        return gcd(m % n, n);
    }
}
