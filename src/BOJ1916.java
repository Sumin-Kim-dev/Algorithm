import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
    final int INF = Integer.MAX_VALUE;
    int n;
    Integer[][] bus;
    int start, end;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        bus = new Integer[n][n];
        StringTokenizer st;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int busStart = Integer.parseInt(st.nextToken()) - 1;
            int busEnd = Integer.parseInt(st.nextToken()) - 1;
            int busCost = Integer.parseInt(st.nextToken());
            if(bus[busStart][busEnd] == null || busCost < bus[busStart][busEnd])
                bus[busStart][busEnd] = busCost;
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;
    }

    // 다익스트라 알고리즘
    int Dijkstra() {

        class City implements Comparable<City>{
            final int city, cost;
            City(int city, int cost) {
                this.city = city;
                this.cost = cost;
            }
            public int compareTo(City c) {
                return this.cost - c.cost;
            }
        }

        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        cost[start] = 0;
        while(!pq.isEmpty()) {
            City curr = pq.poll();
            int currCity = curr.city;
            int currCost = curr.cost;
            if(cost[currCity] < currCost)
                continue;
            for(int i = 0; i < n; i++) {
                if(bus[currCity][i] == null)
                    continue;
                if(cost[i] > currCost + bus[currCity][i]) {
                    cost[i] = currCost + bus[currCity][i];
                    pq.add(new City(i, cost[i]));
                }
            }
        }
        return cost[end];
    }

    void solution() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input();
        bw.write(Dijkstra()+"");
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1916().solution();
    }
}
