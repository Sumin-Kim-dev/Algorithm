import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] answer = new BOJ2436().solution(n, m);
        StringBuilder sb = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append(' ');
        }
        System.out.println(sb);
    }

    int[] solution(int n, int m) {
        if (m % n != 0) return new int[]{};
        int ab = m / n;
        int[] disjoint = disjoint(ab);
        return new int[]{n * disjoint[0], n * disjoint[1]};
    }

    int[] disjoint(int ab) {
        int[] disjoint = new int[2];
        for (int i = 1; i * i <= ab; i++) {
            if (ab % i != 0) continue;
            if (gcd(i, ab / i) == 1) {
                disjoint[0] = i;
                disjoint[1] = ab / i;
            }
        }
        return disjoint;
    }

    int gcd(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
