import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ1385 {
    final int MAX = 1000520;
    int start, end;

    int getN(int level, int angle, int order) {
        if (level == 0) return 1;
        return 2 + 3 * (level - 1) * level + (angle * level + order + 6 * level) % (6 * level);
    }

    int level(int n) {
        int level = 0;
        int curr = 1;
        while (n > curr) {
            curr += 6 * (++level);
        }
        return level;
    }

    int angle(int n) {
        if (n == 1) return 0;
        int level = level(n);
        return (n - 2 - 3 * level * (level - 1)) / level;
    }

    int order(int n) {
        if (n == 1) return 0;
        int level = level(n);
        return (n - 2) % level;
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    int[] dist(int start) {
        int[] dist = new int[1000520];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : next(curr)) {
                if (next < MAX && dist[next] == 0) {
                    dist[next] = curr;
                    queue.offer(next);
                    if (next == end) break;
                }
            }
        }
        return dist;
    }

    int[] next(int curr) {
        int[] next = new int[6];
        int level = level(curr);
        int angle = angle(curr);
        int order = order(curr);
        if (level == 0) {
            for (int i = 0; i < 6; i++) {
                next[i] = i + 2;
            }
            return next;
        }
        next[0] = getN(level, angle, order - 1);
        next[1] = getN(level, angle, order + 1);
        next[2] = getN(level - 1, angle, order - 1);
        next[3] = getN(level + 1, angle, order);
        next[4] = getN(level + 1, angle, order + 1);
        if (order == level - 1) next[5] = getN(level + 1, angle, order + 2);
        else next[5] = getN(level - 1, angle, order);
        return next;
    }

    void printPath() {
        int[] dist = dist(start);
        Stack<Integer> stack = new Stack<>();
        int curr = end;
        stack.push(end);
        while (curr != start) {
            curr = dist[curr];
            stack.push(curr);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        printPath();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1385().solution();
    }
}
