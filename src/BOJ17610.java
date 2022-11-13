import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17610 {
    int n;
    int[] g;
    int s = 0;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        g = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            g[i] = Integer.parseInt(st.nextToken());
            s += g[i];
        }
        System.out.println(solution());
    }

    boolean[] isAble;
    int[] curr;
    int solution() {
        isAble = new boolean[s + 1];
        curr = new int[n];
        backtracking(0, 0);
        int count = 0;
        for (int i = 1; i <= s; i++) {
            if (!isAble[i]) count++;
        }
        return count;
    }

    void backtracking(int curr, int currSum) {
        if (curr >= n) return;
        for (int i = -1; i <= 1; i++) {
            int nextSum = currSum + i * g[curr];
            if (nextSum >= 0) isAble[nextSum] = true;
            backtracking(curr + 1, nextSum);
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ17610().io();
    }
}
