import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2591 {
    public static void main(String[] args) throws IOException {
        new BOJ2591().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        long[] count = new long[n.length()];
        Arrays.fill(count, -1);
        System.out.println(solution(n, 0, count));
    }

    long solution(String n, int start, long[] count) {
        if (start >= n.length()) return 1;
        if (count[start] >= 0) return count[start];
        if (n.charAt(start) == '0') return count[start] = 0;
        if (start == n.length() - 1) return count[start] = 1;
        count[start] = solution(n, start + 1, count);
        if (start + 1 < n.length() && Integer.parseInt(n.substring(start, start + 2)) <= 34) {
            count[start] += solution(n, start + 2, count);
        }
        return count[start];
    }
}
