import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2824 {
    final int MAX = 1_000_000_000;
    int[] a, b;
    boolean gcdIsBig = false;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        b = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            b[i] = Integer.parseInt(st.nextToken());
    }

    HashMap<Integer, Integer> factorization(int[] a) {
        HashMap<Integer, Integer> factors = new HashMap<>();
        for (int n : a) {
            factors = factorization(n, factors);
        }
        return factors;
    }

    HashMap<Integer, Integer> factorization(int n, HashMap<Integer, Integer> factors) {
        if (n == 1) return factors;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                return factorization(n / i, factors);
            }
        }
        factors.put(n, factors.getOrDefault(n, 0) + 1);
        return factors;
    }

    long pow(int prime, int order) {
        long pow = 1;
        while (order-- > 0) {
            pow *= prime;
            if (pow >= MAX) gcdIsBig = true;
            pow %= MAX;
        }
        return pow;
    }

    long gcd(HashMap<Integer, Integer> factorsA, HashMap<Integer, Integer> factorsB) {
        long gcd = 1;
        for (Integer primeA : factorsA.keySet()) {
            int orderA = factorsA.get(primeA);
            int orderB = factorsB.getOrDefault(primeA, 0);
            gcd *= pow(primeA, Math.min(orderA, orderB));
            if (gcd >= MAX) gcdIsBig = true;
            gcd %= MAX;
        }
        return gcd;
    }

    void solution() throws IOException {
        input();
        HashMap<Integer, Integer> factorsA = factorization(a);
        HashMap<Integer, Integer> factorsB = factorization(b);
        long gcd = gcd(factorsA, factorsB);
        if (gcdIsBig) System.out.printf("%09d", gcd);
        else System.out.println(gcd);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2824().solution();
    }
}
