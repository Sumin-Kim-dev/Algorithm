import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {
    int n;
    int[] select;
    int[] visitedOrder;
    boolean[] isFinished;
    int noTeam;

    void input(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        select = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            select[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        visitedOrder = new int[n];
        isFinished = new boolean[n];
    }

    void dfs(int start, int currOrder) {
        visitedOrder[start] = currOrder + 1;
        int next = select[start];
        if (isFinished[next]) {
            isFinished[start] = true;
            return;
        }
        if (visitedOrder[next] == 0) dfs(next, visitedOrder[start]);
        else noTeam -= (visitedOrder[start] + 1 - visitedOrder[next]);
        isFinished[start] = true;
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            input(br);
            noTeam = n;
            for (int i = 0; i < n; i++) {
                if (visitedOrder[i] == 0) dfs(i, 0);
            }
            sb.append(noTeam).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ9466().solution();
    }
}
