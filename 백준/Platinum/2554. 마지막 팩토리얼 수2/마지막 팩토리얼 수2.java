import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static final int[] LAST_DIGIT = {1, 1, 1, 3, 3, 3, 3, 1, 1, 9, 9, 9, 9, 7, 7, 7, 7, 9, 9, 1};
    public static final int[] LAST_DIGIT2 = {6, 2, 4, 8};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger n = new BigInteger(br.readLine());
        System.out.println(new Main().solution(n));
    }

    public int solution(BigInteger n) {
        int answer = 1;

        if (n.compareTo(BigInteger.ONE) <= 0) return answer;

        int count2 = 0;
        int count5 = 0;

        // 5의 배수가 아닌 홀수들의 곱
        answer *= lastDigit(n);

        // 10의 배수들의 곱 중 0 아닌 끝자리 = (n/10)!의 0 아닌 끝자리
        answer *= solution(n.divide(BigInteger.TEN));

        // 10의 배수가 아닌 짝수들의 곱 = 2^(n/2) * (1 * 2 * 3 * 4 * 6 * 7 * 8 * 9 * ...)
        BigInteger temp = n;
        while (temp.compareTo(BigInteger.ONE) >= 0) {
            temp = temp.divide(BigInteger.TWO);
            int remainder = temp.remainder(BigInteger.valueOf(20)).intValue();
            count2 += (remainder % 5 == 0 ? 0 : remainder % 5); // 10의 배수는 빼고 세야함
            answer = (answer * LAST_DIGIT[remainder]) % 10;
        }

        // 5의 배수인 홀수들의 곱 = 5^(n/5) * (1 * 3 * 5 * 7 * 9 * ...)
        temp = n;
        while (temp.compareTo(BigInteger.ONE) >= 0) {
            temp = temp.divide(BigInteger.valueOf(5));
            int remainder = temp.remainder(BigInteger.valueOf(40)).intValue();
            count5 += (remainder + 1) / 2; // 10의 배수는 빼고 세야함
            answer = (answer * LAST_DIGIT[remainder % 20]) % 10;
        }

        return (answer * LAST_DIGIT2[(count2 - count5 + 4000) % 4]) % 10;
    }

    private int lastDigit(BigInteger n) {
        int remainder = n.remainder(BigInteger.valueOf(20)).intValue();
        return LAST_DIGIT[remainder];
    }
}
