import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
    int[] arr;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String enc = br.readLine();
        arr = new int[enc.length()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = enc.charAt(i) - '0';
    }

    int dp() {
        int[] count = new int[arr.length + 1];
        count[0] = 1;
        for (int i = 1; i <= arr.length; i++) {
            // 첫 한 글자인 경우
            if (i == 1) {
                if (arr[i - 1] == 0) return 0;
                count[i] = 1;
                continue;
            }
            // 뒤의 두 자리가 문자로 변환되지 않는 경우
            if (arr[i - 2] * 10 + arr[i - 1] > 26 || arr[i - 2] == 0) {
                if (arr[i - 1] == 0) return 0;
                count[i] = count[i - 1];
                continue;
            }
            // 뒤의 두 자리가 문자로 변환이 가능한 경우
            count[i] = count[i - 2];
            if (arr[i - 1] != 0) {
                count[i] += count[i - 1];
                count[i] %= 1000000;
            }
        }
        return count[arr.length];
    }

    void solution() throws IOException {
        input();
        System.out.println(dp());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2011().solution();
    }
}
