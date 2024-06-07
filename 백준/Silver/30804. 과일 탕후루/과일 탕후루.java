import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] fruits = new int[n];
        boolean[] change = new boolean[n - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            fruits[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int s = 0;
        int e = 1;
        int max = 1;
        int kind = 1;
        int[] count = new int[9];
        count[fruits[s]] = 1;
        while (s < e && e < n) {
            if (kind == 1 || count[fruits[e]] > 0) {
                if (count[fruits[e]] == 0) kind = 2;
                count[fruits[e++]]++;
                if (e - s > max) max = e - s;
                if (e == n) break;
            } else {
                if (count[fruits[s]] == 1) kind = 1;
                count[fruits[s++]]--;
            }
        }
        System.out.println(max);
    }
}