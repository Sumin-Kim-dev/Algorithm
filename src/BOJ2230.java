import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2230 {
    int n;
    List<Integer> a;

    public static void main(String[] args) throws IOException {
        new BOJ2230().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(a);
        System.out.println(solution(m));
    }

    int solution(int m) {
        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        while (start <= end && end < n) {
            int diff = a.get(end) - a.get(start);
            if (diff >= m) {
                start++;
                if (min > diff) min = diff;
            } else end++;
        }
        return min;
    }
}
