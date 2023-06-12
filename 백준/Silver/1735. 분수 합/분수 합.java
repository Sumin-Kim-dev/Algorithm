import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a1 = Integer.parseInt(st.nextToken());
        int b1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a2 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());
        
        int a = a1 * b2 + b1 * a2;
        int b = b1 * b2;
        int gcd = gcd(a, b);
        System.out.println(a / gcd + " " + b / gcd);
    }
    
    private static int gcd(int a, int b) {
        if (a == 0) return b;
        if (a > b) return gcd(b, a);
        return gcd(b % a, a);
    }
}