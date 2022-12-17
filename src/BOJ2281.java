import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2281 {
    int n, m;
    int[] names;

    public static void main(String[] args) throws IOException {
        new BOJ2281().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        names = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(dp());
    }

    int maxEndLine(int start) {
        int line = names[start];
        int currEnd = start;
        while (currEnd < n - 1) {
            line += 1;
            line += names[currEnd + 1];
            if (line > m) break;
            currEnd++;
        }
        return currEnd;
    }

    int dp() {
        int[] min = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int maxEndLine = maxEndLine(i);
            if (maxEndLine == n - 1) {
                continue;
            }
            int line = 0;
            min[i] = Integer.MAX_VALUE;
            for (int j = i; j <= maxEndLine; j++) {
                line += names[j];
                min[i] = Math.min(min[i], min[j + 1] + (m - line) * (m - line));
                line++;
            }
        }
        return min[0];
    }
}
