import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer>[] a = new Deque[100];
        for (int i = 0; i < 100; i++) {
            a[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < n; i++) {
            int ai = Integer.parseInt(st.nextToken());
            a[ai - 1].addLast(i);
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Deque<Integer>[] b = new Deque[100];
        for (int i = 0; i < 100; i++) {
            b[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < m; i++) {
            int bi = Integer.parseInt(st.nextToken());
            b[bi - 1].addLast(i);
        }
        int k = 0;
        int la = -1;
        int lb = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 100; i >= 1; i--) {
            if (a[i - 1].isEmpty() || b[i - 1].isEmpty() 
                || a[i - 1].peekLast() <= la || b[i - 1].peekLast() <= lb) continue;
            while (!a[i - 1].isEmpty() && a[i - 1].peekFirst() <= la) {
                a[i - 1].pollFirst();
            }
            while (!b[i - 1].isEmpty() && b[i - 1].peekFirst() <= lb) {
                b[i - 1].pollFirst();
            }
            while (!a[i - 1].isEmpty() && !b[i - 1].isEmpty()) {
                la = a[i - 1].pollFirst();
                lb = b[i - 1].pollFirst();
                k++;
                sb.append(i).append(' ');
            }
        }
        System.out.println(k);
        if (k > 0) System.out.println(sb);
    }
}