import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ10159 {
    int n;
    boolean[][] compare;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        compare = new boolean[n][n];
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            compare[a][b] = true;
        }
        int[] answer = solution();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }

    int[] solution() {
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = n - 1 - dfs(i) - dfsReverse(i);
        }
        return answer;
    }

    int dfs(int i) {
        boolean[] isVisited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        isVisited[i] = true;
        int count = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int k = 0; k < n; k++) {
                if (compare[curr][k] && !isVisited[k]) {
                    isVisited[k] = true;
                    stack.push(k);
                    count++;
                }
            }
        }
        return count;
    }

    int dfsReverse(int i) {
        boolean[] isVisited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        isVisited[i] = true;
        int count = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int k = 0; k < n; k++) {
                if (compare[k][curr] && !isVisited[k]) {
                    isVisited[k] = true;
                    stack.push(k);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        new BOJ10159().io();
    }
}
