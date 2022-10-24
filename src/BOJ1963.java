import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1963 {
    boolean[] isNotPrime = new boolean[10000];

    void io() throws IOException {
        setPrime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            sb.append(solution(i, f)).append('\n');
        }
        System.out.println(sb);
    }

    String solution(int i, int f) {
        if (i == f) return "0";
        boolean[] isChecked = new boolean[10000];
        isChecked[i] = true;
        int[] initial = new int[5];
        for (int index = 1; index <= 4; index++) {
            initial[index] = i % 10;
            i /= 10;
        }
        Queue<int[]> password = new LinkedList<>();
        password.offer(initial);
        while (!password.isEmpty()) {
            int[] curr = password.poll();
            int currPassword = curr[1] + curr[2] * 10 + curr[3] * 100 + curr[4] * 1000;
            int weight = 1;
            for (int index = 1; index <= 4; index++) {
                int[] next = {curr[0] + 1, curr[1], curr[2], curr[3], curr[4]};
                next[index] = 0;
                int nextPassword = currPassword - curr[index] * weight;
                for (int k = 0; k <= 9; k++) {
                    if (!isNotPrime[nextPassword] && !isChecked[nextPassword] && nextPassword >= 1000) {
                        if (nextPassword == f) return next[0] + "";
                        isChecked[nextPassword] = true;
                        password.offer(next.clone());
                    }
                    next[index]++;
                    nextPassword += weight;
                }
                weight *= 10;
            }
        }
        return "Impossible";
    }

    void setPrime() {
        for (int i = 2; i < 100; i++) {
            if (isNotPrime[i]) continue;
            for (int j = 2 * i; j < 10000; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ1963().io();
    }
}
