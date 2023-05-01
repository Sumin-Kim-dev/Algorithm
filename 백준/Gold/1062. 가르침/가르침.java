import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final long A = 0, N = 'n' - 'a', T = 't' - 'a', I = 'i' - 'a', C = 'c' - 'a';
    int n, k;
    long[] words;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        words = new long[n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 4; j < word.length() - 4; j++) {
                char curr = word.charAt(j);
                if (curr == 'a' || curr == 'n' || curr == 't' || curr == 'i' || curr == 'c') continue;
                words[i] |= (1L << (curr - 'a'));
            }
        }
    }

    int max = 0;
    long curr;

    void backtracking(int depth, int last) {
        if (depth == k) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if ((~curr & words[i]) == 0) count++;
            }
            if (count > max) max = count;
            return;
        }
        for (int i = last + 1; i < 26; i++) {
            if (i == A || i == N || i == T || i == I || i == C) continue;
            long bit = 1L << i;
            curr ^= bit;
            backtracking(depth + 1, i);
            curr ^= bit;
        }
    }

    void solution() throws IOException {
        input();
        if (k >= 5) {
            curr = (1L << A) | (1L << N) | (1L << T) | (1L << I) | (1L << C);
            backtracking(5, 0);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
