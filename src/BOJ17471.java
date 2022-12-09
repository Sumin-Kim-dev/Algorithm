import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ17471 {
    int n, sum;
    int[] people;
    List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        new BOJ17471().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            adj[i] = new ArrayList<>();
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
        System.out.println(solution());
    }

    int solution() {
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += people[i];
        }
        int answer = -1;
        for (int bit = 1; bit < (1 << n) - 1; bit += 2) {
            if (!isAble(bit)) continue;
            int diff = diff(bit);
            if (answer == -1 || answer > diff) answer = diff;
        }
        return answer;
    }

    int diff(int bit) {
        int curr = sum;
        for (int i = 0; i < n; i++) {
            if ((bit & 1 << i) == 0) curr -= 2 * people[i];
        }
        return curr > 0 ? curr : -curr;
    }


    boolean isAble(int bit) {
        int other = (1 << n) - bit - 1;
        return isConnected(bit) && isConnected(other);
    }

    boolean isConnected(int bit) {
        boolean[] isVisited = new boolean[n];
        int start = 0;
        for (int i = 0; i < n; i++) {
            isVisited[i] = (bit & 1 << i) == 0;
            if (!isVisited[i]) start = i;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i : adj[curr]) {
                if (isVisited[i]) continue;
                isVisited[i] = true;
                queue.offer(i);
            }
        }
        boolean isConnected = true;
        for (int i = 0; i < n; i++) {
            isConnected &= isVisited[i];
        }
        return isConnected;
    }
}
