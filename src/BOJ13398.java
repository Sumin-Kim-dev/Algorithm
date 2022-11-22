import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398 {
    int n;
    int[] arr;

    public static void main(String[] args) throws IOException {
        new BOJ13398().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] s = new int[n + 1];
        int[] t = new int[n + 1];
        s[1] = arr[0];
        int max = arr[0];
        for (int i = 2; i <= n; i++) {
            s[i] = arr[i - 1] + Math.max(s[i - 1], 0);
            t[i] = arr[i - 1] + Math.max(t[i - 1], s[i - 2]);
            if (max < s[i]) max = s[i];
            if (max < t[i]) max = t[i];
        }
        return max;
    }
}
