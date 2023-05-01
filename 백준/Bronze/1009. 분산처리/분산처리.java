import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(solution(a, b)).append('\n');
        }
        System.out.println(sb);
    }

    static int solution(int a, int b) {
        if (a % 10 == 0) return 10;
        if (a % 10 == 5) return 5;
        b %= 4;
        if (b == 0) b = 4;
        int computer = 1;
        for (int i = 1; i <= b; i++) {
            computer *= a;
        }
        return computer % 10;
    }
}
