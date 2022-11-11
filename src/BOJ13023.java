import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {
    int n;
    List<Integer>[] friends;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        friends = new List[n];
        for (int i = 0; i < n; i++) {
            friends[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }
        System.out.println(solution() ? 1 : 0);
    }

    boolean[] isVisited;

    boolean solution() {
        isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isAble(i)) return true;
        }
        return false;
    }

    boolean isAble(int a) {
        return dfs(a, 0);
    }

    boolean dfs(int a, int count) {
        if (count == 4) return true;
        boolean result = false;
        isVisited[a] = true;
        for (int friend : friends[a]) {
            if (isVisited[friend]) continue;
            result |= dfs(friend, count + 1);
        }
        isVisited[a] = false;
        return result;
    }

    public static void main(String[] args) throws IOException {
        new BOJ13023().io();
    }
}
