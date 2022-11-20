import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    int[] t;
    int[] p;
    int[][] maxCost;

    public static void main(String[] args) throws IOException {
        new BOJ14501().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        maxCost = new int[n][n];
        System.out.println(solution(0, n - 1));
    }

    int solution(int start, int end) {
        if (start > end) return 0;
        if (maxCost[start][end] > 0) return maxCost[start][end];
        if (start == end) {
            if (t[start] == 1) return maxCost[start][end] = p[start];
            else return 0;
        }
        int max = 0;
        if (start + t[start] - 1 <= end) max = solution(start + t[start], end) + p[start];
        max = Math.max(max, solution(start + 1, end));
        return maxCost[start][end] = max;
    }
}
