import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ7490 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            print(solution(n));
        }
    }

    String[] solution(int n) {
        char[] curr = new char[n - 1]; // -1 : -, 0 : 공백, +1 : +
        return backtracking(0, curr, new ArrayList<>()).toArray(String[]::new);
    }

    ArrayList<String> backtracking(int depth, char[] curr, ArrayList<String> answer) {
        if (depth == curr.length) {
            if (calculate(curr) == 0) answer.add(expression(curr));
            return answer;
        }
        for (int i = 0; i < 3; i++) {
            curr[depth] = switch (i) {
                case 1 -> '+';
                case 2 -> '-';
                default -> ' ';
            };
            answer = backtracking(depth + 1, curr, answer);
        }
        return answer;
    }

    int calculate(char[] curr) {
        int answer = 0;
        int i = 0;
        while (i <= curr.length) {
            int num = i + 1;
            int sign = 1;
            if (i > 0 && curr[i - 1] == '-') sign = -1;
            while (i < curr.length && curr[i] == ' ') {
                i++;
                num *= 10;
                num += (i + 1);
            }
            answer += sign * num;
            i++;
        }
        return answer;
    }

    String expression(char[] curr) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < curr.length; i++) {
            sb.append(curr[i]).append(i + 2);
        }
        return sb.append('\n').toString();
    }

    void print(String[] answer) {
        StringBuilder sb = new StringBuilder();
        for (String s : answer) {
            sb.append(s);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ7490().io();
    }
}
