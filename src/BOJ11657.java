import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657 {
    final int INF = 5_000_001;
    int n;
    Bus[] bus;

    static class Bus {
        int start, end, cost;

        Bus(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        bus = new Bus[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            bus[i] = new Bus(start, end, Integer.parseInt(st.nextToken()));
        }
    }

    long[] BellmanFord() {
        long[] minTime = new long[n];
        for (int i = 1; i < n; i++)
            minTime[i] = INF;
        for (int i = 1; i < n; i++) {
            for (Bus b : bus) {
                if (minTime[b.start] != INF && minTime[b.start] + b.cost < minTime[b.end])
                    minTime[b.end] = minTime[b.start] + b.cost;
            }
        }
        for (Bus b : bus) {
            if (minTime[b.start] != INF && minTime[b.start] + b.cost < minTime[b.end])
                return new long[]{-1};
        }
        for (int i = 0; i < n; i++)
            if (minTime[i] == INF) minTime[i] = -1;
        return Arrays.copyOfRange(minTime, 1, minTime.length);
    }

    void solution() throws IOException {
        input();
        StringBuilder sb = new StringBuilder();
        for (long i : BellmanFord()) {
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ11657().solution();
    }
}
