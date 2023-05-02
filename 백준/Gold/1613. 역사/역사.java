import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    int n;
    HashSet<Integer>[] before;
    HashSet<Integer>[] after;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        before = new HashSet[n];
        after = new HashSet[n];
        for (int i = 0; i < n; i++) {
            before[i] = new HashSet<>();
            after[i] = new HashSet<>();
        }
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            before[b].add(a);
            after[a].add(b);
        }
        int s = Integer.parseInt(br.readLine());
        int[][] query = new int[s][2];
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken()) - 1;
            query[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        int[] answer = solution(query);
        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }

    int[] solution(int[][] query) {
        topologicalSort();
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            if (before[query[i][1]].contains(query[i][0])) {
                answer[i] = -1;
            } else if (before[query[i][0]].contains(query[i][1])) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    void topologicalSort() {
        int[] beforeCount = new int[n];
        Queue<Integer> first = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            beforeCount[i] = before[i].size();
            if (beforeCount[i] == 0) first.offer(i);
        }
        while (!first.isEmpty()) {
            int curr = first.poll();
            for (int i : after[curr]) {
                before[i].addAll(before[curr]);
                beforeCount[i]--;
                if (beforeCount[i] == 0) first.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().io();
    }
}
