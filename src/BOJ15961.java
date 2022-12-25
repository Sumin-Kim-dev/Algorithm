import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15961 {
    int n, d, k, c;
    int[] belt;

    public static void main(String[] args) throws IOException {
        new BOJ15961().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken()) - 1;
        belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine()) - 1;
        }
        System.out.println(solution());
    }

    int solution() {
        int answer = 0;
        int[] chobap = new int[d];
        chobap[c] = 1;
        int count = 1;
        for (int i = 0; i < k; i++) {
            chobap[belt[i]]++;
            if (chobap[belt[i]] == 1) count++;
        }
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, count);
            chobap[belt[i]]--;
            if (chobap[belt[i]] == 0) count--;
            chobap[belt[(i + k) % n]]++;
            if (chobap[belt[(i + k) % n]] == 1) count++;
        }
        return answer;
    }
}
