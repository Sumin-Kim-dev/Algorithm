import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2170 {
    int n;
    int[][] lines;

    public static void main(String[] args) throws IOException {
        new BOJ2170().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));
        System.out.println(solution());
    }

    int solution() {
        int length = 0;
        int start = lines[0][0];
        int end = lines[0][1];
        for (int i = 1; i < n; i++) {
            if (lines[i][0] <= end) {
                end = Math.max(lines[i][1], end);
            } else {
                length += (end - start);
                start = lines[i][0];
                end = lines[i][1];
            }
        }
        length += (end - start);
        return length;
    }
}
