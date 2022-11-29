import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6198 {

    public static void main(String[] args) throws IOException {
        new BOJ6198().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution(n, h));
    }

    long solution(int n, int[] h) {
        long answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(h[0]);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && h[i] >= stack.peek()) {
                stack.pop();
            }
            answer += stack.size();
            stack.push(h[i]);
        }
        return answer;
    }
}
