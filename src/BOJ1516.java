import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
    int n;
    int[] cost;
    HashSet<Integer>[] before;
    HashSet<Integer>[] after;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n];
        before = new HashSet[n];
        after = new HashSet[n];
        for (int i = 0; i < n; i++) {
            after[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            before[i] = new HashSet<>();
            while (st.hasMoreTokens()) {
                int curr = Integer.parseInt(st.nextToken());
                if (curr == -1) break;
                before[i].add(curr - 1);
                after[curr - 1].add(i);
            }
        }
        int[] totalCost = solution();
        StringBuilder sb = new StringBuilder();
        for (int i : totalCost) {
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }

    int[] solution() {
        int[] answer = new int[n];
        int[] beforeCount = new int[n];
        Queue<Integer> first = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            beforeCount[i] = before[i].size();
            if (beforeCount[i] == 0) first.offer(i);
        }
        while (!first.isEmpty()) {
            int curr = first.poll();
            for (int i : before[curr]) {
                if (answer[i] > answer[curr]) {
                    answer[curr] = answer[i];
                }
            }
            answer[curr] += cost[curr];
            for (int i : after[curr]) {
                beforeCount[i]--;
                if (beforeCount[i] == 0) first.offer(i);
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        new BOJ1516().io();
    }
}
