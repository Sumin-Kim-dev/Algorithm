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
        List<Integer> goal = setGoal(k);
        boolean[][] isVisited = new boolean[MAX + 1][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});
        isVisited[n][0] = true;
        int min = MAX;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int t = Collections.binarySearch(goal, curr[0]);
            if (t >= curr[1] && (t - curr[1]) % 2 == 0 && min > t) min = t;
            if (curr[0] - 1 >= 0) {
                int[] next = {curr[0] - 1, curr[1] + 1};
                if (!isVisited[next[0]][next[1] % 2]) {
                    isVisited[next[0]][next[1] % 2] = true;
                    queue.offer(next);
                }
            }
            if (curr[0] + 1 <= MAX) {
                int[] next = {curr[0] + 1, curr[1] + 1};
                if (!isVisited[next[0]][next[1] % 2]) {
                    isVisited[next[0]][next[1] % 2] = true;
                    queue.offer(next);
                }
            }
            if (2 * curr[0] <= MAX) {
                int[] next = {2 * curr[0], curr[1] + 1};
                if (!isVisited[next[0]][next[1] % 2]) {
                    isVisited[next[0]][next[1] % 2] = true;
                    queue.offer(next);
                }
            }
        }
        return min < MAX ? min : -1;
    }

    List<Integer> setGoal(int k) {
        List<Integer> goal = new ArrayList<>();
        int i = 1;
        int currGoal = k;
        while (currGoal <= MAX) {
            goal.add(currGoal);
            currGoal += i;
            i++;
        }
        return goal;
    }
}
