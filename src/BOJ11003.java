import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11003 {
    int n, l;
    int[] arr;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n + l];
        for (int i = 0; i < n + l; i++) {
            if (i < l) arr[i] = Integer.MAX_VALUE;
            if (i >= l) arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    void findMin() {
        StringBuilder ans = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < l; i++)
            pq.add(new int[]{i - l, arr[i]});
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{i, arr[i + l]});
            int minIndex = pq.peek()[0];
            while (minIndex < i - l + 1) {
                pq.poll();
                minIndex = pq.peek()[0];
            }
            ans.append(pq.peek()[1]).append(' ');
        }
        System.out.println(ans);
    }

    void solution() throws IOException {
        input();
        findMin();
    }

    public static void main(String[] args) throws IOException {
        new BOJ11003().solution();
    }
}
