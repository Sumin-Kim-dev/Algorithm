import java.io.*;
import java.util.*;

public class Main {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        System.out.println(solution(min, max));
    }

    int solution(long min, long max) {
        ArrayList<Long> primeSquareList = setPrime();
        HashSet<Long> nono = new HashSet<>();
        for (long i = min; i <= max; i++) {
            nono.add(i);
        }
        for (long primeSquare : primeSquareList) {
            long a = min / primeSquare * primeSquare;
            for (long i = a; i <= max; i += primeSquare) {
                nono.remove(i);
            }
        }
        return nono.size();
    }

    ArrayList<Long> setPrime() {
        ArrayList<Long> primeSquareList = new ArrayList<>();
        boolean[] isNotPrime = new boolean[1000001];
        for (int i = 2; i <= 1000; i++) {
            if (isNotPrime[i]) continue;
            for (int j = 2 * i; j <= 1000000; j += i) {
                isNotPrime[j] = true;
            }
        }
        for (long i = 2; i <= 1000000; i++) {
            if (!isNotPrime[(int) i]) primeSquareList.add(i * i);
        }
        return primeSquareList;
    }

    public static void main(String[] args) throws IOException {
        new Main().io();
    }
}