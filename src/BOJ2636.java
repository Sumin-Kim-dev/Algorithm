import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    static final int[][] NEXT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int OUTSIDE = -2, INSIDE = -1;
    int n, m, numOfCheese, finalCheese;
    int[][] cheese;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numOfCheese = 0;
        cheese = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int curr = Integer.parseInt(st.nextToken());
                if (curr == 0) cheese[i][j] = INSIDE;
                if (curr == 1) {
                    cheese[i][j] = 0;
                    numOfCheese++;
                }
            }
        }
        setCheese();
    }

    void initializeCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cheese[i][j] > 0) cheese[i][j] = 0;
            }
        }
    }

    void setCheese() {
        boolean[][] isChecked = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        cheese[0][0] = OUTSIDE;
        isChecked[0][0] = true;
        initializeCheese();
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = curr[0] + NEXT[i][0];
                int nextJ = curr[1] + NEXT[i][1];
                if (!isValid(nextI, nextJ)) continue;
                if (cheese[nextI][nextJ] >= 0) cheese[nextI][nextJ]++;
                else if (!isChecked[nextI][nextJ]) {
                    isChecked[nextI][nextJ] = true;
                    cheese[nextI][nextJ] = OUTSIDE;
                    queue.add(new int[]{nextI, nextJ});
                }
            }
        }
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    int time() {
        int time = 0;
        while (numOfCheese > 0) {
            finalCheese = numOfCheese;
            time++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cheese[i][j] < 1) continue;
                    cheese[i][j] = INSIDE;
                    numOfCheese--;
                }
            }
            setCheese();
        }
        return time;
    }

    void solution() throws IOException {
        input();
        System.out.println(time());
        System.out.println(finalCheese);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2636().solution();
    }
}
