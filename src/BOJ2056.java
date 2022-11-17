import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2056 {
    int n;
    int[] time;
    List<Integer>[] before;

    public static void main(String[] args) throws IOException {
        new BOJ2056().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new int[n];
        before = new List[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            before[i] = new ArrayList<>(Integer.parseInt(st.nextToken()));
            while (st.hasMoreTokens()) {
                before[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
        System.out.println(solution());
    }

    int solution() {
        int[] minTime = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            minTime[i] = time[i] + before[i].stream().mapToInt(j -> minTime[j]).max().orElse(0);
            if (max < minTime[i]) max = minTime[i];
        }
        return max;
    }
}
