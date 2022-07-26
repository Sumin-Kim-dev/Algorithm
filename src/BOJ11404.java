import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404 {
    static final int INF = 100000000;
    int n;
    int[][] bus;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        bus = new int[n][n];
        int m = Integer.parseInt(br.readLine());
        int start, end, cost;
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            cost = Integer.parseInt(st.nextToken());
            if (bus[start][end] == 0 || cost < bus[start][end])
                bus[start][end] = cost;
        }
    }

    int[][] FloydWarshall() {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bus[i][j] != 0) dist[i][j] = bus[i][j];
                else if (i != j) dist[i][j] = INF;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

    void print(int[][] arr) throws IOException {
        for (int[] ints : arr) {
            StringBuilder sb = new StringBuilder();
            for (int anInt : ints) {
                int printValue = anInt;
                if (anInt == INF) printValue = 0;
                sb.append(printValue).append(' ');
            }
            System.out.println(sb);
        }
    }

    void solution() throws IOException {
        input();
        print(FloydWarshall());
    }

    public static void main(String[] args) throws IOException {
        new BOJ11404().solution();
    }
}
