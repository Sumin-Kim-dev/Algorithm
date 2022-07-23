import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1939 {
    int n, m;
    int start, end;
    int[][] bridge;
    int[] set;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        bridge = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            bridge[i][0] = Integer.parseInt(st.nextToken()); // 다리 시작
            bridge[i][1] = Integer.parseInt(st.nextToken()); // 다리 끝
            bridge[i][2] = Integer.parseInt(st.nextToken()); // 중량 제한
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    int binarySearch() {
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (isAble(mid)) start = mid;
            else end = mid;
        }
        return start;
    }

    boolean isAble(int w) {
        set = new int[n + 1];
        for (int i = 1; i <= n; i++)
            set[i] = -1;
        for (int i = 0; i < m; i++) {
            if (w <= bridge[i][2]) union(bridge[i][0], bridge[i][1]);
        }
        return findSet(start) == findSet(end);
    }

    void union(int start, int end) {
        int setStart = findSet(start);
        int setEnd = findSet(end);
        if (setStart == setEnd) return;
        if (-set[setStart] < -set[setEnd]) {
            int temp = setStart;
            setStart = setEnd;
            setEnd = temp;
        }
        if (set[setStart] == set[setEnd])
            set[setStart]--;
        set[setEnd] = setStart;
    }

    int findSet(int a) {
        if (set[a] < 0) return a;
        return set[a] = findSet(set[a]);
    }

    void solution() throws IOException {
        input();
        System.out.println(binarySearch());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1939().solution();
    }
}
