import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class BOJ1918 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String eq = br.readLine();
        System.out.println(solution(eq));
    }

    String solution(String eq) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> opStack = new Stack<>();

        HashMap<Character, Integer> op = new HashMap<>();
        op.put('*', 1);
        op.put('/', 1);
        op.put('+', 0);
        op.put('-', 0);

        for (int i = 0; i < eq.length(); i++) {
            char c = eq.charAt(i);
            if (Character.isAlphabetic(c)) { // 피연산자가 나오면 답에 추가
                sb.append(c);
                continue;
            }
            if (c == '(') { // 열린 괄호
                opStack.push(c);
                continue;
            }
            if (c == ')') { // 닫힌 괄호 : 열린 괄호 뒤에 나온 연산자 처리
                while (true) {
                    if (opStack.isEmpty()) break; // 처리가 끝났음
                    char ch = opStack.pop();
                    if (ch == '(') break; // 처리가 끝났음
                    sb.append(ch); // 연산자 처리
                }
                continue;
            }
            while (!opStack.isEmpty()) { // 연산자가 나온 경우
                char ch = opStack.peek();
                if (ch == '(') break; // 열린 괄호보다 앞에 나온 연산자는 처리하면 안됨
                if (op.get(c) > op.get(ch)) break; // 우선순위가 낮은 연산자는 처리하면 안됨
                opStack.pop();
                sb.append(ch); // 우선순위가 높은 연산자 처리
            }
            opStack.push(c);
        }
        while (!opStack.isEmpty()) {
            sb.append(opStack.pop()); // 남은 연산자 처리
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1918().io();
    }
}
