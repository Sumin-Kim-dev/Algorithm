import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) break;
            sb.append(perfect(n)).append('\n');
        }
        System.out.println(sb);
    }
    
    private static String perfect(int n) {
        ArrayList<String> divisors = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                divisors.add(String.valueOf(i));
                sum += i;
            }
        }
        if (sum != n) return String.format("%d is NOT perfect.", n);
        return String.format("%d = %s", n, String.join(" + ", divisors));
    }
}