import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14671 {
    int n, m;
    boolean[][] molds = new boolean[2][2];

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x, y;
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            molds[x % 2][y % 2] = true;
        }
    }

    void solution() throws IOException {
        input();
        if (molds[0][0] && molds[0][1] && molds[1][0] && molds[1][1])
            System.out.println("YES");
        else System.out.println("NO");
    }

    public static void main(String[] args) throws IOException {
        new BOJ14671().solution();
    }
}
