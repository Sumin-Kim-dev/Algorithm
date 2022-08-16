import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1865 {
    static final int INF = 10_000_000;
    int n;
    Integer[][] time;

    void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        time = new Integer[n][n];
        setRoads(m, br);
        setWormHoles(w, br);
    }

    void setRoads(int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        int s, e, t;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken());
            if (time[s][e] == null || t < time[s][e])
                time[s][e] = time[e][s] = t;
        }
    }

    void setWormHoles(int w, BufferedReader br) throws IOException {
        StringTokenizer st;
        int s, e, t;
        while (w-- > 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            t = -Integer.parseInt(st.nextToken());
            if (time[s][e] == null || t < time[s][e])
                time[s][e] = t;
        }
    }

    String FloydWarshall() {
        int[][] minTime = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (time[i][i] != null && time[i][i] < 0) return "YES";
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (time[i][j] == null)
                    minTime[i][j] = INF;
                else minTime[i][j] = time[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (minTime[i][k] == INF || minTime[k][j] == INF) continue;
                    if (minTime[i][k] + minTime[k][j] < minTime[i][j])
                        minTime[i][j] = minTime[i][k] + minTime[k][j];
                }
                if (minTime[i][i] < 0) return "YES";
            }
        }
        return "NO";
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            input(br);
            System.out.println(FloydWarshall());
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ1865().solution();
    }
}
