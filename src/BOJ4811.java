import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4811 {
    long[] catalan = new long[31];

    void io() throws IOException {
        catalan();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            sb.append(catalan[n]).append('\n');
            n = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }

    void catalan() {
        catalan[0] = 1;
        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ4811().io();
    }
}
