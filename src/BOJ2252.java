import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2252 {
    int n;
    ArrayList<Integer>[] order;
    boolean[] isChecked;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        order = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            order[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(st.nextToken());
        int front, back;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            front = Integer.parseInt(st.nextToken()) - 1;
            back = Integer.parseInt(st.nextToken()) - 1;
            order[front].add(back);
        }
    }

    void topologicalSort() {
        isChecked = new boolean[n];
        Stack<Integer> sorted = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (isChecked[i]) continue;
            dfs(i, sorted);
        }
        print(sorted);
    }

    void dfs(int i, Stack<Integer> sorted) {
        if (isChecked[i]) return;
        isChecked[i] = true;
        for (int next : order[i]) {
            dfs(next, sorted);
        }
        sorted.push(i + 1);
    }

    void print(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        topologicalSort();
    }

    public static void main(String[] args) throws IOException {
        new BOJ2252().solution();
    }
}
