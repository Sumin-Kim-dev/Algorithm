import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052 {

    public static void main(String[] args) throws IOException {
        new BOJ11052().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(n, cost));
    }

    int solution(int n, int[] cost) {
        int[] maxCost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                maxCost[i] = Math.max(maxCost[i], maxCost[i - j - 1] +cost[j]);
            }
        }
        return maxCost[n];
    }
}
