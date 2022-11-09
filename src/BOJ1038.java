import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1038 {
    int[][] comb = new int[11][11];

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    long solution(int n) {
        setComb();
        int[] subCount = subCount();
        int start = 0;
        int end = 11;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (subCount[mid] <= n) start = mid;
            else end = mid;
        }
        if (end == 11) return -1;
        return solution(end, n - subCount[start]);
    }

    long solution(int digits, int rank) {
        if (digits == 1) return rank;
        int[] subCount = subCount(digits);
        int start = 0;
        int end = 10;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (subCount[mid] <= rank) start = mid;
            else end = mid;
        }
        return start * (long) Math.pow(10, digits - 1)
                + solution(digits - 1, rank - subCount[start]);
    }

    int[] subCount(int digits) {
        int[] subCount = new int[10];
        for (int i = 1; i <= 9; i++) {
            subCount[i] = subCount[i - 1] + comb[i - 1][digits - 1];
        }
        return subCount;
    }

    int[] subCount() {
        int[] subCount = new int[11];
        for (int i = 1; i <= 10; i++) {
            subCount[i] = subCount[i - 1] + comb[10][i];
        }
        return subCount;
    }

    void setComb() {
        comb[0][0] = 1;
        for (int i = 1; i <= 10; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ1038().io();
    }
}
