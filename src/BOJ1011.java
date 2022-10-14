import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1011 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(solution(y - x)).append('\n');
        }
        System.out.println(sb);
    }

    int solution(int x) {
        if (x <= 3) return x;
        int max = (int) Math.sqrt(x);
        return (x - 1) / max + max;
    }

    public static void main(String[] args) throws IOException {
        new BOJ1011().io();
    }
}
