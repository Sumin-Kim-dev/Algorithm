import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2812 {
    int n, k;
    int[] numbers;
    Queue<Integer>[] index;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        index = new Queue[10];
        for (int i = 0; i < 10; i++)
            index[i] = new LinkedList<>();
        String number = br.readLine();
        for (int i = 0; i < n; i++) {
            numbers[i] = number.charAt(i) - '0';
            index[numbers[i]].offer(i);
        }
    }

    int currIndex = 0;

    int greedy() {
        if (k == 0) {
            return numbers[currIndex++];
        }
        int curr;
        for (int i = 9; i >= 0; i--) {
            curr = -1;
            while (!index[i].isEmpty()) {
                curr = index[i].peek();
                if (curr >= currIndex) break;
                index[i].poll();
            }
            if (curr < currIndex || curr > currIndex + k) continue;
            k -= (curr - currIndex);
            currIndex = curr + 1;
            return i;
        }
        return 0;
    }

    void solution() throws IOException {
        input();
        StringBuilder sb = new StringBuilder();
        int length = n - k;
        for (int i = 0; i < length; i++) {
            sb.append(greedy());
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2812().solution();
    }
}
