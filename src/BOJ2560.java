import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2560 {
    int a, b, d;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        System.out.println(solution(N));
    }

    int solution(int N) {
        int[] bugs = new int[N + 1];
        bugs[0] = 1;
        bugs[a] = bugs[0];
        for (int i = a + 1; i < b; i++) {
            if (i > N) break;
            bugs[i] = (bugs[i - 1] + bugs[i - a]) % 1000;
        }
        for (int i = b; i <= N; i++) {
            bugs[i] = (bugs[i - 1] + bugs[i - a] - bugs[i - b] + 1000) % 1000;
        }
        int sum = 0;
        for (int i = 0; i < d; i++) {
            if (N < i) break;
            sum = (sum + bugs[N - i]) % 1000;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2560().io();
    }
}
