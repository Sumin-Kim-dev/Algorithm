import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19598 {
    int n;
    List<int[]> time;

    public static void main(String[] args) throws IOException {
        new BOJ19598().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time.add(new int[]{Integer.parseInt(st.nextToken()), 0});
            time.add(new int[]{Integer.parseInt(st.nextToken()), 1});
        }
        System.out.println(count());
    }

    int count() {
        time.sort((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            else return o2[1] - o1[1];
        });
        int count = 0;
        int max = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (time.get(i)[1] == 0) count++;
            else count--;
            max = Math.max(max, count);
        }
        return max;
    }
}
