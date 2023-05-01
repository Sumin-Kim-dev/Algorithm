import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int n, k, w;
    int[] time;
    int[] minTime;
    ArrayList<Integer>[] parents;

    void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = new int[n];
        parents = new ArrayList[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            parents[i] = new ArrayList<>();
        }
        int before, after;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            before = Integer.parseInt(st.nextToken()) - 1;
            after = Integer.parseInt(st.nextToken()) - 1;
            parents[after].add(before);
        }
        w = Integer.parseInt(br.readLine()) - 1;
    }

    int time(int w) {
        if (minTime[w] != -1) {
            return minTime[w];
        }
        int maxTime = 0;
        int curr;
        for (int i : parents[w]) {
            curr = time(i);
            if (curr > maxTime) maxTime = curr;
        }
        minTime[w] = maxTime + time[w];
        return minTime[w];
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            input(br);
            minTime = new int[n];
            Arrays.fill(minTime, -1);
            System.out.println(time(w));
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
