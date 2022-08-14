import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14938 {
    static final int INF = 5000;
    int n, m;
    int[] items;
    int[][] graph;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        setItems(new StringTokenizer(br.readLine()));
        setGraph(r, br);
    }

    void setItems(StringTokenizer st) {
        items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
    }

    void setGraph(int r, BufferedReader br) throws IOException {
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                graph[i][j] = INF;
            }
        }
        StringTokenizer st;
        int a, b, length;
        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            length = Integer.parseInt(st.nextToken());
            if (length < graph[a][b])
                graph[a][b] = graph[b][a] = length;
        }
    }

    int[][] FloydWarshall() {
        int[][] minDist = graph.clone();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (minDist[i][k] + minDist[k][j] < minDist[i][j])
                        minDist[i][j] = minDist[i][k] + minDist[k][j];
                }
            }
        }
        return minDist;
    }

    int maxItems(int[][] minDist) {
        int maxItems = 0;
        for (int i = 0; i < n; i++) {
            int currItems = 0;
            for (int j = 0; j < n; j++) {
                if (minDist[i][j] <= m)
                    currItems += items[j];
            }
            if (currItems > maxItems)
                maxItems = currItems;
        }
        return maxItems;
    }

    void solution() throws IOException {
        input();
        System.out.println(maxItems(FloydWarshall()));
    }

    public static void main(String[] args) throws IOException {
        new BOJ14938().solution();
    }
}
