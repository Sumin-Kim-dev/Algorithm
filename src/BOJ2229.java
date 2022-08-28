import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2229 {
    int n;
    int[] scores;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        scores = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
    }

    int dp() {
        int[] max = new int[n + 1];
        int diff;
        for (int i = 1; i <= n; i++) {
            max[i] = max[i - 1];
            diff = 0;
            for (int j = i - 1; j > 0; j--) {
                if (Math.abs(scores[i - 1] - scores[j - 1]) > diff) {
                    diff = Math.abs(scores[i - 1] - scores[j - 1]);
                }
                if (max[j - 1] + diff > max[i]) {
                    max[i] = max[j - 1] + diff;
                }
            }
        }
        return max[n];
    }

    void solution() throws IOException {
        input();
        System.out.println(dp());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2229().solution();
    }
}
