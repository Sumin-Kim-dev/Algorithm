import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1956 {
    static final int MAX = 100000000;
    int v, e;
    int[][] roads;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        roads = new int[v][v];
        int start, end, dist;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            roads[start][end] = dist;
        }
    }

    int[] FloydWarshall() {
        int[][] dist = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (roads[i][j] != 0) dist[i][j] = roads[i][j];
                else dist[i][j] = MAX;
            }
        }
        for (int k = 0; k < v; k++) {
            int[][] currDist = dist.clone();
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    dist[i][j] = Math.min(currDist[i][j], currDist[i][k] + currDist[k][j]);
                }
            }
        }
        int[] cycle = new int[v];
        for(int i = 0; i < v; i++)
            cycle[i] = dist[i][i];
        return cycle;
    }

    int getMin(int[] arr) {
        int min = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(min > arr[i]) min = arr[i];
        }
        if(min == MAX) return -1;
        return min;
    }

    void solution() throws IOException {
        input();
        System.out.println(getMin(FloydWarshall()));
    }

    public static void main(String[] args) throws IOException {
        new BOJ1956().solution();
    }
}
