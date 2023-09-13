import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n + 1];
        int[] numbers = new int[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(st.nextToken());
            numbers[x] = i;
        }
        for (int i = 1; i <= 1000000; i++) {
            if (numbers[i] == 0) continue;
            for (int j = 2 * i; j <= 1000000; j += i) {
                if (numbers[j] == 0) continue;
                scores[numbers[i]]++;
                scores[numbers[j]]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(scores[i]).append(' ');
        }
        System.out.println(sb);
    }
}