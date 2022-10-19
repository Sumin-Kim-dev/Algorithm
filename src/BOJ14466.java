import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14466 {
    int n, k, r;
    HashSet<Integer> cows;
    HashSet<Integer>[] roads;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        roads = new HashSet[n * n];
        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken()) - 1;
            int startC = Integer.parseInt(st.nextToken()) - 1;
            int endR = Integer.parseInt(st.nextToken()) - 1;
            int endC = Integer.parseInt(st.nextToken()) - 1;
            int start = startR * n + startC;
            int end = endR * n + endC;
            if (roads[start] == null) roads[start] = new HashSet<>();
            if (roads[end] == null) roads[end] = new HashSet<>();
            roads[start].add(end);
            roads[end].add(start);
        }
        cows = new HashSet<>(k);
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int cowR = Integer.parseInt(st.nextToken()) - 1;
            int cowC = Integer.parseInt(st.nextToken()) - 1;
            cows.add(cowR * n + cowC);
        }
        System.out.println(solution());
    }

    boolean[][] farm;

    int solution() {
        int answer = k * (k - 1) / 2;
        farm = new boolean[n][n];
        int[] cowList = cows.stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < k; i++) {
            if (!cows.contains(cowList[i])) continue;
            answer -= visit(cowList[i]);
        }
        return answer;
    }

    final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int visit(int cow) {
        int cowR = cow / n;
        int cowC = cow % n;
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{cowR, cowC});
        farm[cowR][cowC] = true;
        cows.remove(cow);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currNum = curr[0] * n + curr[1];
            for (int[] dir : DIR) {
                int[] next = {curr[0] + dir[0], curr[1] + dir[1]};
                int nextNum = next[0] * n + next[1];
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                if (roads[currNum] != null && roads[currNum].contains(nextNum)) continue;
                if (farm[next[0]][next[1]]) continue;
                queue.offer(next);
                farm[next[0]][next[1]] = true;
                if (cows.contains(nextNum)) {
                    count++;
                    cows.remove(nextNum);
                }
            }
        }
        return count * (count - 1) / 2;
    }

    public static void main(String[] args) throws IOException {
        new BOJ14466().io();
    }
}
