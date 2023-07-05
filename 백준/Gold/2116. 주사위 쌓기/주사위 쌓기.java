import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dices = new int[n][6];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int k = 0; k <= 5; k++) {
            int curr = max(dices, k);
            if (curr > max) max = curr;
        }
        System.out.println(max);
    }
    
    private static int max(int[][] dices, int k) {
        int[] opp = {5, 3, 4, 1, 2, 0};
        int max = 0;
        for (int i = 0; i < dices.length; i++) {
            int currMax = 0;
            for (int j = 0; j < 6; j++) {
                if (j == k || j == opp[k]) continue;
                if (dices[i][j] > currMax) currMax = dices[i][j];
            }
            max += currMax;
            int next = dices[i][opp[k]];
            if (i + 1 < dices.length) {
                for (int j = 0; j < 6; j++) {
                    if (dices[i + 1][j] == next) {
                        k = j;
                        break;
                    }
                }
            }
        }
        return max;
    }
}