import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2529 {
    int k;
    boolean[] increase;
    boolean[] isVisited = new boolean[10];
    int[] seq;
    String[] answer = new String[2];

    public static void main(String[] args) throws IOException {
        new BOJ2529().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        increase = new boolean[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            if (st.nextToken().charAt(0) == '<') increase[i] = true;
        }
        solution();
    }

    void solution() {
        seq = new int[k + 1];
        backtracking(0, 1);
        System.out.println(answer[1]);

        backtracking(0, 0);
        System.out.println(answer[0]);
    }

    void backtracking(int depth, int order) {
        if (!(answer[order] == null)) return;
        if (depth == k + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= k; i++) {
                sb.append(seq[i]);
            }
            answer[order] = sb.toString();
            return;
        }
        for (int i = 0; i <= 9; i++) {
            int j = 9 * order + (1 - 2 * order) * i;
            if (isVisited[j]) continue;
            if (depth > 0 && (seq[depth - 1] < j ^ increase[depth - 1])) continue;
            isVisited[j] = true;
            seq[depth] = j;
            backtracking(depth + 1, order);
            seq[depth] = 0;
            isVisited[j] = false;
        }
    }
}
