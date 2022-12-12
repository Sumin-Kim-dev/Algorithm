import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
    public static void main(String[] args) throws IOException {
        new BOJ2138().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] diff = new boolean[n];
        String curr = br.readLine();
        String result = br.readLine();
        for (int i = 0; i < n; i++) {
            diff[i] = curr.charAt(i) != result.charAt(i);
        }
        System.out.println(solution(n, diff));
    }

    int solution(int n, boolean[] diff) {
        int count1 = count(n, diff, true);
        int count2 = count(n, diff, false);
        int count = Math.min(count1, count2);
        return count <= n ? count : -1;
    }

    int count(int n, boolean[] diff, boolean start) {
        boolean[] switches = new boolean[n];
        switches[0] = start;
        int count = 0;
        if (start) count++;
        for (int i = 1; i < n; i++) {
            switches[i] = diff[i - 1] ^ switches[i - 1];
            if (i > 1) switches[i] ^= switches[i - 2];
            if (switches[i]) count++;
        }
        if (diff[n - 1] != switches[n - 2] ^ switches[n - 1]) return Integer.MAX_VALUE;
        return count;
    }
}
