import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10835 {
    int n;
    int[] a, b;
    int[][] max;

    public static void main(String[] args) throws IOException {
        new BOJ10835().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st1.nextToken());
            b[i] = Integer.parseInt(st2.nextToken());
        }
        max = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(max[i], -1);
        }
        solution(0, 0);
        System.out.println(max[0][0]);
    }

    int solution(int currA, int currB) {
        if (currA >= n || currB >= n) {
            return 0;
        }
        if (max[currA][currB] >= 0) return max[currA][currB];
        max[currA][currB] = solution(currA + 1, currB + 1);
        int nextA = currA;
        while (nextA < n && a[nextA] <= b[currB]) {
            nextA++;
        }
        if (nextA < n) {
            int next = solution(nextA, currB + 1);
            if (max[currA][currB] < b[currB] + next) {
                max[currA][currB] = b[currB] + next;
            }
        }
        return max[currA][currB];
    }
}
