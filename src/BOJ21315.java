import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21315 {
    public static void main(String[] args) throws IOException {
        new BOJ21315().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        int[] solution = solution(n, cards);
        System.out.println(solution[0] + " " + solution[1]);
    }

    int[] solution(int n, int[] cards) {
        int k1 = Integer.toBinaryString(n - cards[0]).length() - 1;
        int i = 0;
        while (cards[i] != n) {
            i++;
        }
        int k2 = Integer.toBinaryString(i).length() - 1;
        return new int[]{k1, k2};
    }
}
