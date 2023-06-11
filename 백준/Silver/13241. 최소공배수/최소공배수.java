import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long gcd = gcd(a, b);
        long lcm = a * b / gcd;
        System.out.println(lcm);
    }
    
    static long gcd(long a, long b) {
        if(a == 0 || b == 0) return a + b;
        if(a >= b) return gcd(a % b, b);
        else return gcd(a, b % a);
    }
}