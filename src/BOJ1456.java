import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1456 {
    final int MAX = 10_000_000;
    long a, b;
    boolean[] isNotPrime = new boolean[MAX + 1];

    void setPrime() {
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i = 2; i * i <= MAX; i++) {
            if(isNotPrime[i]) continue;
            for(int j = 2 * i; j <= MAX; j += i)
                isNotPrime[j] = true;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
    }

    void solution() throws IOException {
        setPrime();
        input();
        long count = 0;
        for(long j = 2; j <= MAX; j++) {
            if(isNotPrime[(int)j]) continue;
            long curr = j * j;
            while(curr <= b) {
                if(curr >= a) count++;
                if(j > 100_000) break;
                curr *= j;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ1456().solution();
    }
}
