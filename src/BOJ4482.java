import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4482 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            int m = Integer.parseInt(br.readLine());
            int ans = m * (m + 1) * (m + 2) /6;
            sb.append(i).append(": ").append(m).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ4482().solution();
    }
}
