import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9997 {
    public static final int GOAL = (1 << 26) - 1;

    public static void main(String[] args) throws IOException {
        new BOJ9997().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] words = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : br.readLine().toCharArray()) {
                words[i] |= (1 << (c - 'a'));
            }
        }
        System.out.println(solution(n, words));
    }

    private int solution(int n, int[] words) {
        int[] accSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            accSum[i] = accSum[i + 1] |= words[i];
        }
        if (accSum[0] != GOAL) return 0;
        int i = 0;
        int answer = 0;
        while (i < n && accSum[i] == GOAL) {
            answer += dfs(i, words[i], words, accSum);
            i++;
        }
        return answer;
    }

    private int dfs(int i, int curr, int[] words, int[] accSum) {
        if (curr == GOAL) {
            return 1 << (words.length - i - 1);
        }
        int start = i + 1;
        int answer = 0;
        while (start < words.length && (curr | accSum[start]) == GOAL) {
            answer += dfs(start, curr | words[start], words, accSum);
            start++;
        }
        return answer;
    }
}
