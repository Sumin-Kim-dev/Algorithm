import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 2_000_000;
    int n, x;
    int[][] roads;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        setRoads(m, br);
    }

    void setRoads(int m, BufferedReader br) throws IOException {
        roads = new int[n][n];
        StringTokenizer st;
        int start, end;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            roads[start][end] = Integer.parseInt(st.nextToken());
        }
    }

    int[][] reverse(int[][] roads) {
        int[][] reverse = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reverse[i][j] = roads[j][i];
            }
        }
        return reverse;
    }

    int[] Dijkstra(int start, int[][] roads) {
        class City {
            int cityNo;
            int minTime;

            public City(int cityNo, int minTime) {
                this.cityNo = cityNo;
                this.minTime = minTime;
            }
        }

        int[] minTime = new int[n];
        Arrays.fill(minTime, INF);
        minTime[start] = 0;
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.minTime));
        pq.add(new City(start, 0));
        while (!pq.isEmpty()) {
            City currCity = pq.poll();
            if (minTime[currCity.cityNo] < currCity.minTime) continue;
            for (int i = 0; i < n; i++) {
                if (roads[currCity.cityNo][i] == 0) continue;
                if (minTime[currCity.cityNo] + roads[currCity.cityNo][i] < minTime[i]) {
                    minTime[i] = minTime[currCity.cityNo] + roads[currCity.cityNo][i];
                    pq.add(new City(i, minTime[i]));
                }
            }
        }
        return minTime;
    }

    int max(int start) {
        int[] goingTime = Dijkstra(x, roads);
        int[] returningTime = Dijkstra(x, reverse(roads));
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < goingTime[i] + returningTime[i])
                max = goingTime[i] + returningTime[i];
        }
        return max;
    }

    void solution() throws IOException {
        input();
        System.out.println(max(x));
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
