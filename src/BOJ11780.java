import java.io.*;
import java.util.StringTokenizer;

public class BOJ11780 {
    static final int INF = 1_000_000_000;

    int n;
    int[][] bus;
    Cost[][] minCost;
    Path[][] path;

    static class Cost {
        int cost;
        int intermidiate;

        Cost(int cost, int intermidiate) {
            if (cost == 0) this.cost = INF;
            else this.cost = cost;
            this.intermidiate = intermidiate;
        }
    }

    static class Path {
        int length;
        String path;

        Path(int length, String path) {
            this.length = length;
            this.path = path;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        bus = new int[n][n];
        minCost = new Cost[n][n];
        path = new Path[n][n];
        StringTokenizer st;
        int start, end, cost;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            cost = Integer.parseInt(st.nextToken());
            if (bus[start][end] == 0 || cost < bus[start][end])
                bus[start][end] = cost;
        }
    }

    void initializeMinPath() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minCost[i][j] = new Cost(bus[i][j], -1);
            }
        }
    }

    void FloydWarshall() {
        initializeMinPath();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (minCost[i][k].cost + minCost[k][j].cost < minCost[i][j].cost) {
                        minCost[i][j].cost = minCost[i][k].cost + minCost[k][j].cost;
                        minCost[i][j].intermidiate = k;
                    }
                }
            }
        }
    }

    String printMinCost() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || minCost[i][j].cost == INF) sb.append(0).append(' ');
                else sb.append(minCost[i][j].cost).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    Path traceback(int i, int j) {
        if (path[i][j] != null) return path[i][j];
        if (i == j || minCost[i][j].cost == INF)
            return path[i][j] = new Path(-1, "");
        if (minCost[i][j].intermidiate == -1)
            return path[i][j] = new Path(1, " ");
        int k = minCost[i][j].intermidiate;
        Path ik = traceback(i, k);
        Path kj = traceback(k, j);
        return path[i][j] = new Path(ik.length + kj.length,
                ik.path + (k + 1) + kj.path);
    }

    String printPath(int i, int j, Path path) {
        StringBuilder sb = new StringBuilder();
        sb.append(path.length + 1);
        if (path.length > 0) {
            sb.append(' ').append(i + 1).append(path.path).append(j + 1);
        }
        return sb.toString();
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(printMinCost());
        bw.flush();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(printPath(i, j, traceback(i, j)));
                bw.newLine();
                bw.flush();
            }
        }
        bw.close();
    }

    void solution() throws IOException {
        input();
        FloydWarshall();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ11780().solution();
    }
}
