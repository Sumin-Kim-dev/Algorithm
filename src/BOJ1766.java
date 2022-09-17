import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766 {
    int n;
    ArrayList<Integer>[] order;
    int[] inputs;
    PriorityQueue<Integer> emptyInputs;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        order = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            order[i] = new ArrayList<>();
        }
        inputs = new int[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            order[first].add(second);
            inputs[second]++;
        }
    }

    void setEmptyInputs() {
        emptyInputs = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (inputs[i] == 0) emptyInputs.offer(i);
        }
    }

    String topologicalSort() {
        setEmptyInputs();
        StringBuilder sb = new StringBuilder();
        while (!emptyInputs.isEmpty()) {
            int curr = emptyInputs.poll();
            sb.append(curr + 1).append(' ');
            for (int next : order[curr]) {
                inputs[next]--;
                if (inputs[next] == 0) emptyInputs.offer(next);
            }
        }
        return sb.toString();
    }

    void solution() throws IOException {
        input();
        System.out.println(topologicalSort());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1766().solution();
    }
}
