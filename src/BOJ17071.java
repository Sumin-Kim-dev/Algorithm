import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ17071 {
    static final int MAX = 500000;

    public static void main(String[] args) throws IOException {
        new BOJ17071().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(solution(n, k));
    }

    int solution(int n, int k) {
        if (n == k) return 0;
        boolean[][] isVisited = new boolean[MAX + 1][2];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        isVisited[n][0] = true;
        int t = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == k) return t;
                if (curr - 1 >= 0) {
                    int next = curr - 1;
                    if (!isVisited[next][(t + 1) % 2]) {
                        isVisited[next][(t + 1) % 2] = true;
                        queue.offer(next);
                    }
                }
                if (curr + 1 <= MAX) {
                    int next = curr + 1;
                    if (!isVisited[next][(t + 1) % 2]) {
                        isVisited[next][(t + 1) % 2] = true;
                        queue.offer(next);
                    }
                }
                if (2 * curr <= MAX) {
                    int next = 2 * curr;
                    if (!isVisited[next][(t + 1) % 2]) {
                        isVisited[next][(t + 1) % 2] = true;
                        queue.offer(next);
                    }
                }
            }
            t++;
            k += t;
            if (k > MAX) return -1;
            if (isVisited[k][t % 2]) return t;
        }
        return -1;
    }
}
