import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] ad = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ad[i][0] = Integer.parseInt(st.nextToken());
            ad[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(c, n, ad));
    }

    private int solution(int c, int n, int[][] ad) {
        int[] money = new int[c + 1];
        Arrays.fill(money, Integer.MAX_VALUE);
        money[0] = 0;
        for (int i = 1; i <= c; i++) {
            for (int[] city : ad) {
                int before = Math.max(i - city[1], 0);
                money[i] = Math.min(money[i], money[before] + city[0]);
            }
        }
        return money[c];
    }
}