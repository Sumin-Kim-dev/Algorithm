import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    int n;
    int[] seq;
    String goodSeq = "";

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        seq = new int[n];
        backtracking(0);
        System.out.println(goodSeq);
    }

    void backtracking(int depth) {
        if (!goodSeq.isEmpty()) return;
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(seq[i]);
            }
            goodSeq = sb.toString();
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (depth > 0 && seq[depth - 1] == i) continue;
            seq[depth] = i;
            if (isGood(seq, depth)) backtracking(depth + 1);
            seq[depth] = 0;
        }
    }

    boolean isGood(int[] seq, int depth) {
        for (int i = 1; i <= (depth + 1) / 2; i++) {
            boolean isBad = true;
            for (int j = 0; j < i; j++) {
                isBad &= (seq[depth - j] == seq[depth - i - j]);
            }
            if (isBad) return false;
        }
        return true;
    }
}
