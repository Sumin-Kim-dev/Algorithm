import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14267 {
    int n;
    int[] like;
    ArrayList<Integer>[] down;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        down = new ArrayList[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            down[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int up = Integer.parseInt(st.nextToken()) - 1;
            if (up < 0) continue;
            down[up].add(i);
        }
        like = new int[n];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            like[Integer.parseInt(st.nextToken()) - 1] += Integer.parseInt(st.nextToken());
        }
        totalLike(0);
        System.out.println(String.join(" ",
                Arrays.stream(like)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new)));
    }

    void totalLike(int i) {
        for (int d : down[i]) {
            like[d] += like[i];
            totalLike(d);
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ14267().io();
    }
}
