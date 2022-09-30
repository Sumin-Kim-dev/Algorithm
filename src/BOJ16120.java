import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16120 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }

    String solution(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'P') {
                stack.push('P');
                continue;
            }
            if (stack.size() < 2 || i == str.length() - 1 || str.charAt(i + 1) == 'A') return "NP";
            stack.pop();
            stack.pop();
        }
        return stack.size() == 1 ? "PPAP" : "NP";
    }

    public static void main(String[] args) throws IOException {
        new BOJ16120().io();
    }
}
