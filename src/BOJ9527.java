import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9527 {
    long a, b;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
    }

    long count(long a) {
        if(a == 0 || a == 1) return a;
        int digits = 0;
        long powOf2 = 1;
        while(powOf2 * 2 <= a) {
            powOf2 *= 2;
            digits++;
        }
        return digits * powOf2 / 2 + (a - powOf2 + 1) + count(a - powOf2);
    }

    void solution() throws IOException {
        input();
        System.out.println(count(b) - count(a - 1));
    }

    public static void main(String[] args) throws IOException {
        new BOJ9527().solution();
    }
}
