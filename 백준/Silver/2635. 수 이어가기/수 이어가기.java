import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max = 1;
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> seq = seq(n, i);
            if (seq.size() > max) {
                max = seq.size();
                answer = seq;
            }
        }
        System.out.println(max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i)).append(' ');
        }
        System.out.println(sb);
    }
    
    private static List<Integer> seq(int n, int i) {
        List<Integer> seq = new ArrayList<>();
        seq.add(n);
        seq.add(i);
        while (n >= i) {
            int next = n - i;
            seq.add(next);
            n = i;
            i = next;
        }
        return seq;
    }
}