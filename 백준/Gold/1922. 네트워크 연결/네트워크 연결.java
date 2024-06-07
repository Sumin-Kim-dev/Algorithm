import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] p = new int[n];
        Arrays.fill(p, -1);
        StringTokenizer st;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int[] a = new int[3];
            a[0] = Integer.parseInt(st.nextToken()) - 1;
            a[1] = Integer.parseInt(st.nextToken()) - 1;
            a[2] = Integer.parseInt(st.nextToken());
            pq.offer(a);
        }
        int cost = 0;
        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            if(!union(e[0], e[1], p)) continue;
            cost += e[2];
        }
        System.out.println(cost);
    }
    
    static boolean union(int a, int b, int[] p) {
        if (find(a, p) == find(b, p)) return false;
        a = find(a, p);
        b = find(b, p);
        if (-p[a] < -p[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        p[a] += p[b];
        p[b] = a;
        return true;
    }
    
    static int find(int a, int[] p) {
        if (p[a] < 0) return a;
        return p[a] = find(p[a], p);
    }
}