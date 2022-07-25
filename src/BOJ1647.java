import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {
    int n;
    PriorityQueue<int[]> roads;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        roads = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int[] currRoad = new int[3];
            currRoad[0] = Integer.parseInt(st.nextToken()) - 1;
            currRoad[1] = Integer.parseInt(st.nextToken()) - 1;
            currRoad[2] = Integer.parseInt(st.nextToken());
            roads.add(currRoad);
        }
    }

    int kruskal() {
        int[] set = new int[n];
        Arrays.fill(set, -1);
        int numOfRoads = 0;
        int cost = 0;
        while(numOfRoads < n - 2) {
            int[] currRoad = roads.poll();
            if(!union(currRoad[0], currRoad[1], set)) continue;
            numOfRoads++;
            cost += currRoad[2];
        }
        return cost;
    }

    boolean union(int a, int b, int[] set) {
        int aSet = findSet(a, set);
        int bSet = findSet(b, set);
        if (aSet == bSet) return false;
        if (-set[aSet] < -set[bSet]) {
            int temp = aSet;
            aSet = bSet;
            bSet = temp;
        }
        if (set[aSet] == set[bSet]) {
            set[bSet] = aSet;
            set[aSet]--;
        } else set[bSet] = aSet;
        return true;
    }

    int findSet(int a, int[] set) {
        if (set[a] < 0) return a;
        return set[a] = findSet(set[a], set);
    }

    void solution() throws IOException {
        input();
        System.out.println(kruskal());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1647().solution();
    }
}
