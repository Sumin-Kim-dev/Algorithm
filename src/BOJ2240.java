import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {
    int t, w;
    int[] pos;

    public static void main(String[] args) throws IOException {
        new BOJ2240().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        pos = new int[t];
        for (int i = 0; i < t; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution());
    }

    int solution() {
        int[][][] max = new int[t][w + 1][2];
        int answer = 1;
        if (pos[0] == 1) max[0][0][0] = 1;
        else max[0][1][1] = 1;
        for (int i = 1; i < t; i++) {
            for (int j = 0; j <= w; j++) {
                for (int k = 0; k < 2; k++) {
                    max[i][j][k] = max[i - 1][j][k];
                    if (j > 0 && max[i][j][k] < max[i - 1][j - 1][1 - k]) {
                        max[i][j][k] = max[i - 1][j - 1][1 - k];
                    }
                    if (pos[i] == k + 1) max[i][j][k]++;
                    if (max[i][j][k] > answer) answer = max[i][j][k];
                }
            }
        }
        return answer;
    }
}
