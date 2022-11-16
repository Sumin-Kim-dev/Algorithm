import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2212 {
    public static void main(String[] args) throws IOException {
        new BOJ2212().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(n, k, sensors));
    }

    long solution(int n, int k, int[] sensors) {
        Arrays.sort(sensors);
        PriorityQueue<Integer> dist = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            dist.offer(sensors[i] - sensors[i - 1]);
        }
        long sum = 0;
        for (int i = 1; i <= Math.max(0, n - k); i++) {
            sum += dist.poll();
        }
        return sum;
    }
}
