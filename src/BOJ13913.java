import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ13913 {
    final int MAX = 200000;
    int n, k;
    int[][] bfs = new int[MAX + 1][2];

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    void bfs() {
        Queue<Integer> position = new LinkedList<>();
        position.add(n);
        bfs[n][0] = 1;
        bfs[n][1] = n;
        while (!position.isEmpty()) {
            int curr = position.poll();
            if (curr == k) break;
            int[] next = {curr - 1, curr + 1, 2 * curr};
            for (int i : next) {
                if (i < 0 || i > MAX) continue;
                if (bfs[i][0] != 0) continue;
                bfs[i][0] = bfs[curr][0] + 1;
                bfs[i][1] = curr;
                position.add(i);
            }
        }
    }

    void traceback() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> traceback = new Stack<>();
        int curr = k;
        traceback.push(curr);
        while (curr != n) {
            curr = bfs[curr][1];
            traceback.push(curr);
        }
        while (!traceback.isEmpty()) {
            sb.append(traceback.pop()).append(' ');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        bfs();
        System.out.println(bfs[k][0] - 1);
        traceback();
    }

    public static void main(String[] args) throws IOException {
        new BOJ13913().solution();
    }
}
