import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class BOJ2800 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        String[] solution = solution(expression);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    int[] currPairs;
    boolean[] isDeleted;
    TreeSet<String> answer = new TreeSet<>();

    String[] solution(String expression) {
        ArrayList<int[]> pairs = setPairs(expression);
        currPairs = new int[pairs.size()];
        isDeleted = new boolean[expression.length()];
        backtracking(0, pairs, expression);
        return answer.toArray(String[]::new);
    }

    void backtracking(int depth, ArrayList<int[]> pairs, String expression) {
        if (depth == pairs.size()) {
            return;
        }
        int start = 1;
        if (depth > 0) start = currPairs[depth - 1] + 1;
        for (int i = start; i <= pairs.size(); i++) {
            currPairs[depth] = i;
            isDeleted[pairs.get(i - 1)[0]] = isDeleted[pairs.get(i - 1)[1]] = true;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < isDeleted.length; j++) {
                if (isDeleted[j]) continue;
                sb.append(expression.charAt(j));
            }
            answer.add(sb.toString());
            backtracking(depth + 1, pairs, expression);
            isDeleted[pairs.get(i - 1)[0]] = isDeleted[pairs.get(i - 1)[1]] = false;
        }
    }

    ArrayList<int[]> setPairs(String expression) {
        ArrayList<int[]> pairs = new ArrayList<>();
        Stack<Integer> open = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                open.push(i);
            } else if (expression.charAt(i) == ')') {
                pairs.add(new int[]{open.pop(), i});
            }
        }
        return pairs;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2800().io();
    }
}
