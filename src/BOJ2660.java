import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ2660 {
    int n;
    List<Integer>[] friends;

    public static void main(String[] args) throws IOException {
        new BOJ2660().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        friends = new List[n];
        for (int i = 0; i < n; i++) {
            friends[i] = new ArrayList<>();
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == -2 && b == -2) break;
            friends[a].add(b);
            friends[b].add(a);
        }
        solution();
    }

    void solution() {
        Map<Integer, List<Integer>> scores = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int currScore = score(i);
            if (!scores.containsKey(currScore)) {
                scores.put(currScore, new ArrayList<>());
            }
            scores.get(currScore).add(i);
        }
        int minScore = scores.keySet().toArray(Integer[]::new)[0];
        List<Integer> candidates = scores.get(minScore);
        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(' ').append(candidates.size()).append('\n');
        candidates.forEach(i -> sb.append(i + 1).append(' '));
        System.out.println(sb);
    }

    int score(int a) {
        int[] dist = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        dist[a] = 1;
        int score = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int friend : friends[curr]) {
                if (dist[friend] > 0) continue;
                dist[friend] = dist[curr] + 1;
                if (score < dist[friend]) score = dist[friend];
                queue.offer(friend);
            }
        }
        return score - 1;
    }
}
