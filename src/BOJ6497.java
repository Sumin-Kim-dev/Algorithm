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
        PriorityQueue<Road> pq = new PriorityQueue<>();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pq.add(new Road(x, y, z));
            sum += z;
        }
        System.out.println(solution(n, sum, pq));
        return true;
    }

    int solution(int n, int sum, PriorityQueue<Road> pq) {
        int cost = sum;
        int cnt = 1;
        int[] set = new int[n];
        Arrays.fill(set, -1);
        while (!pq.isEmpty() && cnt < n) {
            Road curr = pq.poll();
            if (union(curr.start, curr.end, set)) {
                cnt++;
                cost -= curr.dist;
            }
        }
        return cost;
    }

    boolean union(int s, int e, int[] set) {
        int setS = find(s, set);
        int setE = find(e, set);
        if (setS == setE) return false;
        set[setE] = setS;
        return true;
    }

    int find(int a, int[] set) {
        if (set[a] < 0) return a;
        return set[a] = find(set[a], set);
    }

    static class Road implements Comparable<Road> {
        int start, end, dist;

        public Road(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            return dist - o.dist;
        }
    }
}
