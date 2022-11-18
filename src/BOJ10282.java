import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10282 {
    int n;
    List<int[]>[] dependency;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            new BOJ10282().input(br);
        }
    }

    void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken()) - 1;
        dependency = new List[n];
        for (int i = 0; i < n; i++) {
            dependency[i] = new ArrayList<>();
        }
        while (d-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            dependency[b].add(new int[]{a, s});
        }
        solution(c);
    }

    void solution(int c) {
        final int MAX = 100_000_000;
        int[] minTime = new int[n];
        Arrays.fill(minTime, MAX);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{c, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] >= minTime[curr[0]]) continue;
            minTime[curr[0]] = curr[1];
            for (int[] next : dependency[curr[0]]) {
                if (minTime[next[0]] < minTime[curr[0]] + next[1]) continue;
                pq.offer(new int[]{next[0], minTime[curr[0]] + next[1]});
            }
        }
        int time = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (minTime[i] == MAX) continue;
            count++;
            if (time < minTime[i]) time = minTime[i];
        }
        System.out.println(count + " " + time);
    }
}
