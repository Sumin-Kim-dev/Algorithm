import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12761 {
    final int MAX = 100000;
    int a, b, n, m;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    int minDist() {
        int[] position = new int[MAX + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        position[n] = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int[] next = {curr - 1, curr + 1, curr - a, curr + a, curr - b, curr + b, curr * a, curr * b};
            for (int i : next) {
                if (!isValid(i)) continue;
                if (position[i] != 0) continue;
                position[i] = position[curr] + 1;
                queue.offer(i);
                if (i == m) break;
            }
        }
        return position[m] - 1;
    }

    boolean isValid(int i) {
        return i >= 0 && i <= MAX;
    }

    void solution() throws IOException {
        input();
        System.out.println(minDist());
    }

    public static void main(String[] args) throws IOException {
        new BOJ12761().solution();
    }
}
