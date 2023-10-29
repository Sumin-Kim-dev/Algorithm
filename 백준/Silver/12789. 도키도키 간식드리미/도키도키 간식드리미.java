import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int flag = 0;
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() == flag + 1) {
                stack.pop();
                flag++;
            }
            int curr = Integer.parseInt(st.nextToken());
            if (curr == flag + 1) {
                flag++;
                continue;
            }
            stack.push(curr);
        }
        while (!stack.isEmpty() && stack.peek() == flag + 1) {
            stack.pop();
            flag++;
        }
        System.out.println(flag == n ? "Nice" : "Sad");
    }
}