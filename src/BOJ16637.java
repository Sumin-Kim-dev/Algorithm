import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16637 {
    int n;
    int[] nums;
    char[] ops;

    public static void main(String[] args) throws IOException {
        new BOJ16637().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String eq = br.readLine();
        nums = new int[n / 2 + 1];
        ops = new char[n / 2];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) nums[i / 2] = eq.charAt(i) - '0';
            else ops[i / 2] = eq.charAt(i);
        }
        System.out.println(solution());
    }

    boolean[] isHigh;

    long solution() {
        isHigh = new boolean[n / 2];
        return backtracking(0, Integer.MIN_VALUE);
    }

    long backtracking(int start, long currMax) {
        currMax = Math.max(currMax, eval(isHigh));
        for (int i = start; i < n / 2; i++) {
            isHigh[i] = true;
            currMax = backtracking(i + 2, currMax);
            isHigh[i] = false;
        }
        return currMax;
    }

    long eval(boolean[] isHigh) {
        Deque<Integer> dq = new ArrayDeque<>();
        Queue<Character> opQ = new LinkedList<>();
        dq.offerLast(nums[0]);
        for (int i = 0; i < n / 2; i++) {
            if (!isHigh[i]) {
                dq.offerLast(nums[i + 1]);
                opQ.offer(ops[i]);
            } else {
                int curr = dq.pollLast();
                if (ops[i] == '+') dq.offerLast(curr + nums[i + 1]);
                else if (ops[i] == '-') dq.offerLast(curr - nums[i + 1]);
                else dq.offerLast(curr * nums[i + 1]);
            }
        }
        long answer = dq.pollFirst();
        while (!opQ.isEmpty()) {
            char op = opQ.poll();
            if (op == '+') answer += dq.pollFirst();
            else if (op == '-') answer -= dq.pollFirst();
            else answer *= dq.pollFirst();
        }
        return answer;
    }
}
