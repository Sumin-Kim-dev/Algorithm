import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2463 {
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        new BOJ2463().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(x, y, w));
        }
        System.out.println(solution(pq, n));
    }

    int[] parent;
    long solution(PriorityQueue<Edge> pq, int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
        long answer = 0;
        long pairs = 0;
        while(!pq.isEmpty()) {
            Edge curr = pq.poll();
            pairs += union(curr.x, curr.y);
            answer += curr.w * pairs;
            answer %= MAX;
        }
        return answer;
    }

    long union(int a, int b) {
        int setA = findSet(a);
        int setB = findSet(b);
        if (setA == setB) return 0;
        if (-parent[setA] < -parent[setB]) {
            int temp = setB;
            setB = setA;
            setA = temp;
        }
        long sizeA = -parent[setA];
        long sizeB = -parent[setB];
        parent[setA] += parent[setB];
        parent[setB] = setA;
        return (sizeA * sizeB) % MAX;
    }

    int findSet(int a) {
        if (parent[a] < 0) return a;
        return parent[a] = findSet(parent[a]);
    }

    static class Edge implements Comparable<Edge> {
        int x, y, w;

        public Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - w;
        }
    }
}
