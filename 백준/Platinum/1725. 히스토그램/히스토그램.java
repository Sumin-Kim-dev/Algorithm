import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    int n;
    int[] h;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] left = left();
        int[] right = right();
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, h[i] * (right[i] - left[i] + 1));
        }
        return max;
    }

    int[] left() {
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int curr = stack.pop();
                left[curr] = i + 1;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            left[curr] = 0;
        }
        return left;
    }

    int[] right() {
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int curr = stack.pop();
                right[curr] = i - 1;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            right[curr] = n - 1;
        }
        return right;
    }
}
