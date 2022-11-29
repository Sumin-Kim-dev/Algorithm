import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20056 {
    static final int[][] DIR = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        new BOJ20056().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        List<int[]>[][] fireballs = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fireballs[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs[r][c].add(new int[]{m, s, d});
        }
        System.out.println(solution(fireballs, k));
    }

    int solution(List<int[]>[][] fireballs, int k) {
        for (int i = 0; i < k; i++) {
            fireballs = move(fireballs);
        }
        int totalM = 0;
        for (List<int[]>[] lists : fireballs) {
            for (List<int[]> list : lists) {
                for (int[] fireball : list) {
                    totalM += fireball[0];
                }
            }
        }
        return totalM;
    }

    List<int[]>[][] move(List<int[]>[][] fireballs) {
        List<int[]>[][] next = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < fireballs.length; i++) {
            for (int j = 0; j < fireballs[i].length; j++) {
                for (int[] fireball : fireballs[i][j]) {
                    if (fireball[0] == 0) continue;
                    int nextI = (i + fireball[1] * DIR[fireball[2]][0]) % n;
                    int nextJ = (j + fireball[1] * DIR[fireball[2]][1]) % n;
                    if (nextI < 0) nextI += n;
                    if (nextJ < 0) nextJ += n;
                    next[nextI][nextJ].add(fireball);
                }
            }
        }

        for (List<int[]>[] lists : next) {
            for (List<int[]> list : lists) {
                if (list.size() < 2) continue;
                int[] nextBall = list.get(0);
                int dir = list.get(0)[2] % 2;
                boolean dirEq = true;
                int count = list.size();
                for (int k = 1; k < list.size(); k++) {
                    nextBall[0] += list.get(k)[0];
                    nextBall[1] += list.get(k)[1];
                    if (list.get(k)[2] % 2 != dir) dirEq = false;
                }
                dir = dirEq ? 0 : 1;
                list.clear();
                if (nextBall[0] < 5) continue;
                for (int k = 0; k < 4; k++) {
                    list.add(new int[]{nextBall[0] / 5, nextBall[1] / count, dir});
                    dir += 2;
                }
            }
        }
        return next;
    }
}
