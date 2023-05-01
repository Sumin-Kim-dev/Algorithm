import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    Map<Long, Long> a = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long p = Integer.parseInt(st.nextToken());
        long q = Integer.parseInt(st.nextToken());
        a.put(0L, 1L);
        System.out.println(solution(n, p, q));
    }

    long solution(long n, long p, long q) {
        if (a.containsKey(n)) return a.get(n);
        long an = solution(n / p, p, q) + solution(n / q, p, q);
        a.put(n, an);
        return an;
    }
}
