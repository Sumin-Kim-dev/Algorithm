import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> stop = new ArrayList<>(n + 1);
        stop.add(0);
        for (int i = 0; i < n; i++) {
            stop.add(Integer.parseInt(st.nextToken()));
        }
        stop.add(l);
        System.out.println(solution(m, stop));
    }

    int solution(int m, List<Integer> stop) {
        if (stop.get(stop.size() - 1) == m + stop.size() - 1) return 1;
        List<Integer> dist = setDist(stop);
        int start = 1;
        int end = dist.get(dist.size() - 1);
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (minAdd(mid, dist) > m) start = mid;
            else end = mid;
        }
        return end;
    }

    int minAdd(int max, List<Integer> dist) {
        int add = 0;
        for (int d : dist) {
            add += (d - 1) / max;
        }
        return add;
    }

    List<Integer> setDist(List<Integer> stop) {
        List<Integer> dist = new ArrayList<>(stop.size() - 1);
        Collections.sort(stop);
        for (int i = 0; i < stop.size() - 1; i++) {
            dist.add(stop.get(i + 1) - stop.get(i));
        }
        Collections.sort(dist);
        return dist;
    }
}
