import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309 {
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(new BOJ1309().solution(n));
    }

    int solution(int n) {
        int a = 2, b = 1;
        for (int i = 1; i < n; i++) {
            int temp = a;
            a = (a + 2 * b) % MOD;
            b = (temp + b) % MOD;
        }
        return (a + b) % MOD;
    }
}
