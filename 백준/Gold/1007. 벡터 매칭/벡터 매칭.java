import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n;
    int[][] points;
    int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            new Main().io(br);
        }
    }

    void io(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        points = new int[n][2];
        sum = new int[2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
            sum[0] += points[i][0];
            sum[1] += points[i][1];
        }
        System.out.println(solution());
    }

    int[] select;
    double min;

    double solution() {
        select = new int[n / 2];
        min = Double.MAX_VALUE;
        backtracking(0);
        return min;
    }

    void backtracking(int depth) {
        if (depth == n / 2) {
            long[] currSum = {sum[0], sum[1]};
            for (int i : select) {
                currSum[0] -= (2L * points[i][0]);
                currSum[1] -= (2L * points[i][1]);
            }
            min = Math.min(min, Math.sqrt(currSum[0] * currSum[0] + currSum[1] * currSum[1]));
            return;
        }
        int start = 0;
        if (depth > 0) start = select[depth - 1] + 1;
        for (int i = start; i <= n / 2 + depth; i++) {
            select[depth] = i;
            backtracking(depth + 1);
        }
    }
}
