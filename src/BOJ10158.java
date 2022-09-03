import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10158 {
    int w, h;
    int p, q;
    int t;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(br.readLine());
    }

    void solution() throws IOException {
        input();
        StringBuilder sb = new StringBuilder();
        int x = (p + t) % (2 * w);
        int y = (q + t) % (2 * h);
        if (x > w) x = 2 * w - x;
        if (y > h) y = 2 * h - y;
        sb.append(x).append(' ').append(y);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ10158().solution();
    }
}
