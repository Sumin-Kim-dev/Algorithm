import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stack = new int[n];
        int size = 0;
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                stack[size++] = Integer.parseInt(st.nextToken());
                continue;
            } else if (cmd == 2) {
                if (size == 0) sb.append(-1);
                else sb.append(stack[--size]);
            } else if (cmd == 3) {
                sb.append(size);
            } else if (cmd == 4) {
                sb.append(size == 0 ? 1 : 0);
            } else {
                if (size == 0) sb.append(-1);
                else sb.append(stack[size - 1]);
            } 
            sb.append("\n");
        }
        System.out.println(sb);
    }
}