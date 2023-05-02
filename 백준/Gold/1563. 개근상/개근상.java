import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(new Main().solution(n));
    }

    int solution(int n) {
        int[][][] perfect = new int[n][2][3];
        perfect[0] = new int[][]{{1, 1, 0}, {1, 0, 0}};
        for (int k = 1; k < n; k++) {
            perfect[k][0][0] = (perfect[k-1][0][0] + perfect[k-1][0][1] + perfect[k-1][0][2]) % MOD;
            perfect[k][0][1] = perfect[k-1][0][0];
            perfect[k][0][2] = perfect[k-1][0][1];
            perfect[k][1][0] = sum(perfect[k-1]);
            perfect[k][1][1] = perfect[k-1][1][0];
            perfect[k][1][2] = perfect[k-1][1][1];
        }
        return sum(perfect[n - 1]);
    }

    int sum(int[][] arr) {
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sum += anInt;
                sum %= MOD;
            }
        }
        return sum;
    }
}
