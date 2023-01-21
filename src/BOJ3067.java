import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3067 {
    public static void main(String[] args) throws IOException {
        new BOJ3067().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[n];
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int price = Integer.parseInt(br.readLine());
            sb.append(solution(n, coins, price)).append('\n');
        }
        System.out.println(sb);
    }

    int solution(int n, int[] coins, int price) {
        int[] answer = new int[price + 1];
        answer[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int p = coins[i]; p <= price; p++) {
                answer[p] += answer[p - coins[i]];
            }
        }
        return answer[price];
    }
}
