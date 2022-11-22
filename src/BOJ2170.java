import java.io.*;
import java.util.*;

public class BOJ2170 {
    int n;
    List<int[]> lines;

    public static void main(String[] args) throws IOException {
        new BOJ2170().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines.add(new int[]{s, e});
        }
        lines.sort(Comparator.comparingInt(o -> o[0]));
        System.out.println(solution());
    }

    int solution() {
        int length = 0;
        int start = lines.get(0)[0];
        int end = lines.get(0)[1];
        for (int i = 1; i < n; i++) {
            if (lines.get(i)[0] <= end) {
                end = Math.max(lines.get(i)[1], end);
            } else {
                length += (end - start);
                start = lines.get(i)[0];
                end = lines.get(i)[1];
            }
        }
        length += (end - start);
        return length;
    }
}
