import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486 {
    int n;
    int[][] days;

    public static void main(String[] args) throws IOException {
        new BOJ15486().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        days = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            days[i][0] = Integer.parseInt(st.nextToken());
            days[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] max = new int[n + 1];
        for (int start = n - 1; start >= 0; start--) {
            max[start] = max[start + 1];
            if (start + days[start][0] > n) continue;
            max[start] = Math.max(days[start][1] + max[start + days[start][0]], max[start]);
        }
        return max[0];
    }
}
