import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(new BOJ1052().solution(n, k));
    }

    int solution(int n, int k) {
        int answer = 0;
        while (Integer.bitCount(n + answer) > k) {
            answer++;
        }
        return answer;
    }
}
