import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        Set<Integer> primes = getPrimes(1000000);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int count = 0;
            for (int prime : primes) {
                if (n - prime < prime) continue;
                if (primes.contains(n - prime)) count++;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    
    private static Set<Integer> getPrimes(int max) {
        boolean[] isNotPrime = new boolean[max + 1];
        for (int i = 2; i * i <= max; i++) {
            if (isNotPrime[i]) continue;
            for (int j = 2 * i; j <= max; j += i) {
                isNotPrime[j] = true;
            }
        }
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; i <= max; i++) {
            if (!isNotPrime[i]) primes.add(i);
        }
        return primes;
    }
}