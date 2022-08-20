import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2812 {
    int n, k;
    int[] numbers;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        String number = br.readLine();
        for (int i = 0; i < n; i++) {
            numbers[i] = number.charAt(i) - '0';
        }
    }

    int greedy(int currIndex) {
        int max = 0, index = currIndex;
        if (currIndex + k == n) return n; // currIndex + k는 1씩 증가함
        for (int i = currIndex; i <= currIndex + k; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
                index = i;
            }
        }
        k -= (index - currIndex);
        System.out.print(max);
        return index + 1;
    }

    void solution() throws IOException {
        input();
        int currIndex = 0;
        while (k >= 0 && currIndex < n) {
            currIndex = greedy(currIndex);
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ2812().solution();
    }
}
