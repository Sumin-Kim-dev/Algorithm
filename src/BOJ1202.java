import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1202 {
    final int INF = 200_000_000;
    int n, k;
    int[][] jewels;
    int[][] bags;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        jewels = new int[n][2];
        bags = new int[k + 1][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < k; i++) {
            bags[i][0] = Integer.parseInt(br.readLine());
            bags[i][1] = -1;
        }
        bags[k][0] = INF;
        bags[k][1] = -1;
        Arrays.sort(jewels, (j1, j2) -> {
            if (j1[1] != j2[1]) return j2[1] - j1[1]; // 가격 내림차순 정렬
            else return j1[0] - j2[0]; // 무게 오름차순 정렬
        });
        Arrays.sort(bags, Comparator.comparingInt(bag -> -bag[0])); // 무게 내림차순 정렬
    }

    int findBag(int weight) {
        int start = 0;
        int end = k + 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (bags[mid][0] < weight) end = mid;
            else start = mid;
        }
        int findBag = findSet(start);
        if (findBag > 0) union(findBag);
        return findBag;
    }

    int findSet(int a) {
        if (bags[a][1] < 0) return a;
        return bags[a][1] = findSet(bags[a][1]);
    }

    void union(int a) {
        bags[a][1] = a - 1;
    }

    void solution() throws IOException {
        input();
        long maxValue = 0;
        for (int i = 0; i < n; i++) {
            if (findBag(jewels[i][0]) > 0) maxValue += jewels[i][1];
        }
        System.out.println(maxValue);
    }

    public static void main(String[] args) throws IOException {
        new BOJ1202().solution();
    }
}
