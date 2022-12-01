import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17299 {
    int n;
    int[] a;

    public static void main(String[] args) throws IOException {
        new BOJ17299().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        int[] solution = solution(n, a);
        for (int i : solution) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }

    int[] solution(int n, int[] a) {
        int[] ngf = new int[n];
        Arrays.fill(ngf, -1);
        int[] freq = new int[1000001];
        for (int i = 0; i < n; i++) {
            freq[a[i]]++;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && freq[a[i]] > freq[a[stack.peek()]]) {
                ngf[stack.pop()] = a[i];
            }
            stack.push(i);
        }
        return ngf;
    }
}
