import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12869 {
    final int[] ATTACK = {1, 3, 9};
    final int[][] PERMUTATION = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
    int[] scv;
    int[][][] minAttack = new int[61][61][61];

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        scv = new int[3];
        for (int i = 0; i < n; i++)
            scv[i] = Integer.parseInt(st.nextToken());
        for (int i = n; i < 3; i++)
            scv[i] = 0;
    }

    int dp(int[] scv) {
        if (scv[0] == 0 && scv[1] == 0 && scv[2] == 0)
            return 0;
        if (minAttack[scv[0]][scv[1]][scv[2]] != 0)
            return minAttack[scv[0]][scv[1]][scv[2]];
        int min = Integer.MAX_VALUE;
        for (int[] permutation : PERMUTATION) {
            int[] currSCV = new int[3];
            for (int i = 0; i < 3; i++) {
                currSCV[i] = Math.max(scv[i] - ATTACK[permutation[i]], 0);
            }
            int temp = dp(currSCV) + 1;
            if (min > temp)
                min = temp;
        }
        return minAttack[scv[0]][scv[1]][scv[2]] = min;
    }

    void solution() throws IOException {
        input();
        System.out.println(dp(scv));
    }

    public static void main(String[] args) throws IOException {
        new BOJ12869().solution();
    }
}
