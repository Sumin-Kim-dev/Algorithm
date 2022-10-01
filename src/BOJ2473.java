import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] values = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }
        long[] solution = solution(n, values);
        System.out.println(solution[0] + " " + solution[1] + " " + solution[2]);
    }

    long[] solution(int n, long[] values) {
        Arrays.sort(values);
        long currMin = Long.MAX_VALUE;
        long[] solution = new long[3];
        for (int i = 0; i < n - 2; i++) {
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                long curr = values[i] + values[start] + values[end];
                if (currMin > Math.abs(curr)) {
                    currMin = Math.abs(curr);
                    solution = new long[]{values[i], values[start], values[end]};
                }
                if (curr < 0) {
                    start++;
                } else if (curr > 0) {
                    end--;
                } else {
                    return solution;
                }
            }
        }
        return solution;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2473().io();
    }
}
