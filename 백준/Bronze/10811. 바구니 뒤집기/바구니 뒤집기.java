import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] baskets = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            baskets[i] = i;
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            for (int curr = i; curr < i + j - curr; curr++) {
                int swap = baskets[curr];
                baskets[curr] = baskets[i + j - curr];
                baskets[i + j - curr] = swap;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(baskets[i]).append(' ');
        }
        System.out.println(sb);
    }
}