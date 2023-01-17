import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15662 {
    int t, k;
    int[][] gears, rotations;
    int[] upIndex;

    public static void main(String[] args) throws IOException {
        new BOJ15662().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        gears = new int[t][8];
        for (int i = 0; i < t; i++) {
            String gear = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = gear.charAt(j) - '0';
            }
        }
        k = Integer.parseInt(br.readLine());
        rotations = new int[k][2];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotations[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotations[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        upIndex = new int[t];
        for (int i = 0; i < k; i++) {
            rotate(rotations[i][0], rotations[i][1]);
        }
        int count = 0;
        for (int i = 0; i < t; i++) {
            if (gears[i][upIndex[i]] == 1) count++;
        }
        return count;
    }

    int[] rotateDir;
    void rotate(int pos, int dir) {
        rotateDir = new int[t];
        rotateDir[pos] = dir;
        if (pos > 0
                && gears[pos][(upIndex[pos] + 6) % 8] != gears[pos - 1][(upIndex[pos - 1] + 2) % 8]) {
            left(pos - 1, -dir);
        }
        if (pos < t - 1
                && gears[pos][(upIndex[pos] + 2) % 8] != gears[pos + 1][(upIndex[pos + 1] + 6) % 8]) {
            right(pos + 1, -dir);
        }
        for (int i = 0; i < t; i++) {
            upIndex[i] = (upIndex[i] - rotateDir[i] + 8) % 8;
        }
    }

    void left(int pos, int dir) {
        rotateDir[pos] = dir;
        if (pos > 0
                && gears[pos][(upIndex[pos] + 6) % 8] != gears[pos - 1][(upIndex[pos - 1] + 2) % 8]) {
            left(pos - 1, -dir);
        }
    }

    void right(int pos, int dir) {
        rotateDir[pos] = dir;
        if (pos < t - 1
                && gears[pos][(upIndex[pos] + 2) % 8] != gears[pos + 1][(upIndex[pos + 1] + 6) % 8]) {
            right(pos + 1, -dir);
        }
    }
}
