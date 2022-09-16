import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23814 {
    long d, n, m, k;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        d = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
    }

    long answer() {
        long rn = d - n % d == d ? 0 : d - n % d;
        long rm = d - m % d == d ? 0 : d - m % d;
        long rMin = Math.min(rn, rm);
        long rMax = Math.max(rn, rm);
        if (k < rMin) return k; // 빼줄 수 없는 경우
        if (k < rMin + rMax) return (k - rMin) / d == k / d ? k - rMin : k; // 한쪽만 빼줄 수 있는 경우
        // 두 쪽 다 빼주어도 볶음밥에서 얻을 수 있는 군만두의 개수가 안 빼줄 때랑 동일
        if ((k - rMin - rMax) / d == k / d) return k - rMin - rMax;
        // 두쪽 다 빼주면 볶음밥에서 얻을 수 있는 군만두의 개수가 줄어듦
        // 한쪽만 빼주면 이득인 경우
        if ((k - rMin) / d == k / d) return k - rMin;
        // 두쪽 다 빼주는 경우와 아예 안 빼주는 경우의 비교
        return (k - rMin - rMax) / d == k / d - 1 ? k - rMin - rMax : k;
    }

    void solution() throws IOException {
        input();
        System.out.println(answer());
    }

    public static void main(String[] args) throws IOException {
        new BOJ23814().solution();
    }
}
