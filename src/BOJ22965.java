import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22965 {
    int n;
    int[] a;

    public static void main(String[] args) throws IOException {
        new BOJ22965().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[(i + 1) % n] < a[i]) count++;
        }
        if (count >= 2) return 3;
        return a[n - 1] > a[0] ? 1 : 2;
    }
}
