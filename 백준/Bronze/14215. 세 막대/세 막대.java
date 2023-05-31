import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(max(a, b, c));
    }
    
    private static int max(int a, int b, int c) {
        if (a + b <= c) return 2 * (a + b) - 1;
        if (b + c <= a) return 2 * (b + c) - 1;
        if (c + a <= b) return 2 * (c + a) - 1;
        return a + b + c;
    }
}