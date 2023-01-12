import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        new BOJ2493().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int[] result = solution(n, heights);
        print(result);
    }

    int[] solution(int n, int[] heights) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                result[stack.pop()] = i + 1;
            }
            stack.push(i);
        }
        return result;
    }

    void print(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}
