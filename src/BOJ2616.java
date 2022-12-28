import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2616 {
    int n;
    int[] customers;
    int coach;

    public static void main(String[] args) throws IOException {
        new BOJ2616().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        customers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
        }
        coach = Integer.parseInt(br.readLine());
        System.out.println(solution());
    }
    
    int solution() {
        int[][] max = new int[3][n];
        for (int i = 0; i < coach; i++) {
            max[0][0] += customers[i];
        }
        for (int i = 0; i < n - coach; i++) {
            max[0][i + 1] = max[0][i] + customers[i + coach] - customers[i];
        }
        int max1 = 0;
        for (int i = n - 2 * coach; i >= 0; i--) {
            max1 = Math.max(max1, max[0][i + coach]);
            max[1][i] = max[0][i] + max1;
        }
        int max2 = 0;
        for (int i = n - 3 * coach; i >= 0; i--) {
            max2 = Math.max(max2, max[1][i + coach]);
            max[2][i] = max[0][i] + max2;
        }
        return Arrays.stream(max[2]).max().orElse(0);
    }
}
