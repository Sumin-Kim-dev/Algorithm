import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3079 {
    int n, m;
    int[] t;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = new int[n];
        for (int i = 0; i < n; i++)
            t[i] = Integer.parseInt(br.readLine());
        Arrays.sort(t);
    }

    long minTime() {
        long start = m * (long) t[0];
        long end = 0;
        while (start > end + 1) {
            long mid = (start + end) / 2;
            if (maxPeople(mid) >= m) start = mid;
            else end = mid;
        }
        return start;
    }

    long maxPeople(long mid) {
        long people = 0;
        for (int i = 0; i < n; i++)
            people += mid / t[i];
        return people;
    }

    void solution() throws IOException {
        input();
        System.out.println(minTime());
    }

    public static void main(String[] args) throws IOException {
        new BOJ3079().solution();
    }
}
