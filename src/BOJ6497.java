import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6497 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (new BOJ6497().io(br)) ;
    }

    boolean io(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (n == 0 && m == 0) return false;
        int sum = 0;
        List<Road>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            adj[x].add(new Road(y, z));
            adj[y].add(new Road(x, z));
            sum += z;
        }
        System.out.println(solution(n, sum, adj));
        return true;
    }

    int solution(int n, int sum, List<Road>[] adj) {
        int cost = sum;
        boolean[] isSelected = new boolean[n];
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(0, 0));
        while (!pq.isEmpty()) {
            Road curr = pq.poll();
            if (isSelected[curr.end]) continue;
            isSelected[curr.end] = true;
            cost -= curr.dist;
            for (Road road : adj[curr.end]) {
                if (isSelected[road.end]) continue;
                pq.offer(road);
            }
        }
        return cost;
    }

    static class Road implements Comparable<Road> {
        int end, dist;

        public Road(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            return dist - o.dist;
        }
    }
}
