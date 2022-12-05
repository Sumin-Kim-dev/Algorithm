import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2023 {
    final int[] prime = {2, 3, 5, 7};
    final int[] candidate = {1, 3, 7, 9};
    List<Integer> interestingPrimes;

    int n;

    public static void main(String[] args) throws IOException {
        new BOJ2023().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        solution();
    }

    void solution() {
        interestingPrimes = new ArrayList<>();
        for (int p : prime) {
            backtracking(1, p);
        }
        StringBuilder sb = new StringBuilder();
        interestingPrimes.forEach(i -> sb.append(i).append('\n'));
        System.out.println(sb);
    }

    void backtracking(int depth, int curr) {
        if (depth == n) {
            if (isPrime(curr)) interestingPrimes.add(curr);
            return;
        }
        for (int i : candidate) {
            int next = curr * 10 + i;
            if (isPrime(next)) backtracking(depth + 1, next);
        }
    }

    boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
