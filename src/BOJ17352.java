import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17352 {
    public static void main(String[] args) throws IOException {
        new BOJ17352().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] bridges = new int[n - 1][2];
        StringTokenizer st;
        for (int i = 0; i < n - 2; i++) {
            st = new StringTokenizer(br.readLine());
            bridges[i][0] = Integer.parseInt(st.nextToken()) - 1;
            bridges[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        List<Integer> solution = solution(n, bridges);
        System.out.printf("%d %d", solution.get(0) + 1, solution.get(1) + 1);
    }

    private List<Integer> solution(int n, int[][] bridges) {
        int[] set = new int[n];
        Arrays.fill(set, -1);
        for (int[] bridge : bridges) {
            union(set, bridge[0], bridge[1]);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (set[i] == -1) answer.add(i);
        }
        return answer;
    }

    private void union(int[] set, int a, int b) {
        int setA = findSet(set, a);
        int setB = findSet(set, b);
        if (setA == setB) return;
        set[setA] = setB;
    }

    private int findSet(int[] set, int a) {
        if (set[a] < 0) return a;
        return set[a] = findSet(set, set[a]);
    }
}
