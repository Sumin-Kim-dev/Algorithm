import java.io.*;
import java.util.StringTokenizer;

public class BOJ14588 {
    final int INF = 1000;
    int n;
    int[][] adj;

    void input(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        int[][] lines = new int[n][2];
        adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
            adj[i][i] = INF;
            for (int j = 0; j < i; j++) {
                if (isFriend(lines[i], lines[j])) {
                    adj[i][j] = adj[j][i] = 1;
                } else adj[i][j] = adj[j][i] = INF;
            }
        }
    }

    boolean isFriend(int[] a, int[] b) {
        int aMin = Math.min(a[0], a[1]);
        int aMax = Math.max(a[0], a[1]);
        int bMin = Math.min(b[0], b[1]);
        int bMax = Math.max(b[0], b[1]);
        return !(aMax < bMin || bMax < aMin);
    }

    int[][] FloydWarshall() {
        int[][] dist = adj.clone();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input(br);
        int[][] dist = FloydWarshall();
        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (dist[a][b] >= INF) dist[a][b] = -1;
            bw.write(dist[a][b] + "\n");
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new BOJ14588().solution();
    }
}
