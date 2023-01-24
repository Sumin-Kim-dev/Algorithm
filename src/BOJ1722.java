import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1722 {
    public static void main(String[] args) throws IOException {
        new BOJ1722().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (Integer.parseInt(st.nextToken()) == 1) {
            print(solution(n, Long.parseLong(st.nextToken())));
        } else {
            int[] seq = new int[n];
            for (int i = 0; i < n; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(solution(n, seq));
        }
    }

    long solution(int n, int[] seq) {
        long[] factorials = new long[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        long answer = 1;
        boolean[] isUsed = new boolean[n];
        for (int i = 0; i < n; i++) {
            int curr = findIndex(seq[i], isUsed);
            isUsed[seq[i] - 1] = true;
            answer += curr * factorials[n - 1 - i];
        }
        return answer;
    }

    int[] solution(int n, long k) {
        long[] factorials = new long[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        k--;
        int[] seq = new int[n];
        boolean[] isUsed = new boolean[n];
        for (int i = 0; i < n; i++) {
            long curr = k / factorials[n - 1 - i];
            seq[i] = findTerm(curr, isUsed);
            k %= factorials[n - 1 - i];
        }
        return seq;
    }
    
    int findTerm(long curr, boolean[] isUsed) {
        int count = 0;
        int i = 0;
        while (true) {
            if (isUsed[i++]) continue;
            if (count == curr) break;
            count++;
        }
        isUsed[i - 1] = true;
        return i;
    }

    int findIndex(int curr, boolean[] isUsed) {
        int i = 0;
        int index = 0;
        while (i + 1 < curr) {
            if (isUsed[i++]) continue;
            index++;
        }
        return index;
    }

    void print(int[] seq) {
        StringBuilder sb = new StringBuilder();
        for (int a : seq) {
            sb.append(a).append(' ');
        }
        System.out.println(sb);
    }
}
