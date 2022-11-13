import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11509 {
    int n;
    int[] h;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] arrowCount = new int[1000001];
        for (int i = 0; i < n; i++) {
            if (arrowCount[h[i]] > 0) arrowCount[h[i]]--;
            arrowCount[h[i] - 1]++;
        }
        return Arrays.stream(arrowCount).sum();
    }

    public static void main(String[] args) throws IOException {
        new BOJ11509().io();
    }
}
