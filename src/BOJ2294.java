import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2294 {
    public static void main(String[] args) throws IOException {
        new BOJ2294().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Set<Integer> coins = new HashSet<>();
        while (n-- > 0) {
            coins.add(Integer.parseInt(br.readLine()));
        }
        System.out.println(solution(k, coins));
    }

    int solution(int k, Set<Integer> coins) {
        int[] answer = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            answer[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) continue;
                if (i == coin) {
                    answer[i] = 1;
                    break;
                }
                if (answer[i - coin] < Integer.MAX_VALUE) {
                    answer[i] = Math.min(answer[i], answer[i - coin] + 1);
                }
            }
        }
        return answer[k] == Integer.MAX_VALUE ? -1 : answer[k];
    }
}
