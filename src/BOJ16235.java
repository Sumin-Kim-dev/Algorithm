import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16235 {
    int n, m, k;
    int[][] ground;
    int[][] a;
    PriorityQueue<Integer>[][] trees;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ground = new int[n][n];
        a = new int[n][n];
        trees = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
                trees[i][j] = new PriorityQueue<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].offer(age);
        }
        System.out.println(solution());
    }

    int solution() {
        for (int i = 0; i < k; i++) {
            springSummer();
            fallWinter();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += trees[i][j].size();
            }
        }
        return count;
    }

    final int[][] adj = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    void springSummer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PriorityQueue<Integer> next = new PriorityQueue<>();
                boolean isFinished = false;
                while (!trees[i][j].isEmpty()) {
                    int curr = trees[i][j].poll();
                    // spring
                    if (ground[i][j] >= curr && !isFinished) {
                        next.offer(curr + 1);
                        ground[i][j] -= curr;
                    }
                    // summer
                    else {
                        isFinished = true;
                        ground[i][j] += curr / 2;
                    }
                }
                trees[i][j] = next;
            }
        }
    }

    void fallWinter() {
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // fall
                for (int tree : trees[i][j]) {
                    if (tree % 5 != 0) continue;
                    for (int[] dir : adj) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n) continue;
                        trees[nextI][nextJ].offer(1);
                    }
                }
                // winter
                ground[i][j] += a[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ16235().io();
    }
}
