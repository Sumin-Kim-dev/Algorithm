import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20303 {
    int n;
    int[][] set;

    public static void main(String[] args) throws IOException {
        new BOJ20303().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        set = new int[n][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set[i][0] = -1;
            set[i][1] = Integer.parseInt(st.nextToken());
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a, b);
        }
        System.out.println(solution(k));
    }

    int solution(int k) {
        List<int[]> candies = setCandies();
        int[] answer = new int[k];
        for (int[] candy : candies) {
            for (int j = k - 1; j >= -candy[0]; j--) {
                answer[j] = Math.max(answer[j], answer[j + candy[0]] + candy[1]);
            }
        }
        return Arrays.stream(answer).max().orElse(0);
    }

    List<int[]> setCandies() {
        List<int[]> candies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (findSet(i) != i) continue;
            candies.add(set[i]);
        }
        return candies;
    }

    void union(int a, int b) {
        int setA = findSet(a);
        int setB = findSet(b);
        if (setA == setB) return;
        if (-set[setA][0] > -set[setB][0]) {
            int temp = setA;
            setA = setB;
            setB = temp;
        }
        set[setB][0] += set[setA][0];
        set[setB][1] += set[setA][1];
        set[setA][0] = setB;
    }

    int findSet(int a) {
        if (set[a][0] < 0) return a;
        return set[a][0] = findSet(set[a][0]);
    }
}
