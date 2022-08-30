import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623 {
    int n;
    ArrayList<Integer>[] order;
    int[] inputEdges;
    Queue<Integer> noInput;
    Queue<Integer> answer;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        order = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            order[i] = new ArrayList<>();
        }
        inputEdges = new int[n];
        int num, curr, next;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            curr = Integer.parseInt(st.nextToken()) - 1;
            for (int i = 1; i < num; i++) {
                next = Integer.parseInt(st.nextToken()) - 1;
                order[curr].add(next);
                inputEdges[next]++;
                curr = next;
            }
        }
    }

    boolean topologicalSort() {
        noInput = new LinkedList<>();
        answer = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inputEdges[i] == 0) noInput.add(i);
        }
        int curr;
        while (!noInput.isEmpty()) {
            curr = noInput.poll();
            answer.add(curr + 1);
            for (int i : order[curr]) {
                inputEdges[i]--;
                if (inputEdges[i] == 0) noInput.add(i);
            }
        }
        return answer.size() == n;
    }

    void print(boolean b) {
        if (!b) System.out.println("0");
        else {
            StringBuilder sb = new StringBuilder();
            while (!answer.isEmpty()) {
                sb.append(answer.poll()).append('\n');
            }
            System.out.println(sb);
        }
    }

    void solution() throws IOException {
        input();
        print(topologicalSort());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2623().solution();
    }
}
