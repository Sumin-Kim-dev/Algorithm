import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2668 {
    int n;
    int[] next;
    int[][] cycle;
    boolean[] isVisited;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = Integer.parseInt(br.readLine()) - 1;
        }
        setCycle();
        print(cycle);
    }

    void print(int[][] cycle) {
        System.out.println(Arrays.stream(cycle[0]).filter(i -> i > 0).toArray().length);
        for (int i = 0; i < cycle[0].length; i++) {
            if (cycle[0][i] > 0) {
                System.out.println(i + 1);
            }
        }
    }

    void setCycle() {
        cycle = new int[2][n];
        isVisited = new boolean[n];
        Arrays.fill(cycle[0], -1);
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) continue;
            cycle(i, 1);
        }
    }

    int cycle(int i, int rank) {
        if (isVisited[i]) return cycle[0][i];
        isVisited[i] = true;
        cycle[1][i] = rank;
        if (next[i] == i) {
            cycle[1][i] = 1;
            return cycle[0][i] = 1;
        }
        if (isVisited[next[i]]) {
            if (cycle[0][next[i]] >= 0) return cycle[0][i] = 0;
            cycle[0][i] = cycle[1][i] - cycle[1][next[i]] + 1;
            cycle[1][i] = 1;
            return cycle[0][i];
        }
        int cycleSize = cycle(next[i], rank + 1);
        cycle[1][i] = cycle[1][next[i]] + 1;
        if (cycleSize < cycle[1][i]) return cycle[0][i] = 0;
        return cycle[0][i] = cycleSize;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2668().io();
    }
}
