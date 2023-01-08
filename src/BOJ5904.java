import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904 {
    public static void main(String[] args) throws IOException {
        new BOJ5904().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    char solution(int n) {
        int length = 0;
        int k = 0;
        while (true) {
            int next = 2 * length + k + 3;
            if (next > n) break;
            length = next;
            k++;
        }
        if (n - length == 1) return 'm';
        if (n - length <= k + 3) return 'o';
        return solution(n - length - k - 3);
    }
}
