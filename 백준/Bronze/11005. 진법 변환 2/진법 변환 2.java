import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int base = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int digit = num % base;
            if (digit < 10) sb.insert(0, digit);
            else sb.insert(0, (char) (digit - 10 + 'A'));
            num /= base;
        }
        System.out.println(sb);
    }
}