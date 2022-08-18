import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468 {
    int n, min, max;
    int[][] height;
    boolean[][] isChecked;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        min = Integer.MAX_VALUE;
        max = 0;
        height = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                if (height[i][j] < min) min = height[i][j];
                if (height[i][j] > max) max = height[i][j];
            }
        }
    }

    int numOfAreas(int rain) {
        isChecked = new boolean[n][n];
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isChecked[i][j]) continue;
                if (height[i][j] <= rain) continue;
                setArea(i, j, rain);
                area++;
            }
        }
        return area;
    }

    void setArea(int i, int j, int rain) {
        final int[][] NEXT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        if (height[i][j] <= rain) return;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        isChecked[i][j] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int k = 0; k < 4; k++) {
                int currI = curr[0] + NEXT[k][0];
                int currJ = curr[1] + NEXT[k][1];
                if (!isValid(currI, currJ)) continue;
                if (isChecked[currI][currJ]) continue;
                isChecked[currI][currJ] = true;
                if (height[currI][currJ] <= rain) continue;
                queue.add(new int[]{currI, currJ});
            }
        }
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    void solution() throws IOException {
        input();
        int maxOfAreas = 1; // 비가 아예 안 온 경우
        for (int i = min; i < max; i++) {
            maxOfAreas = Math.max(maxOfAreas, numOfAreas(i));
        }
        System.out.println(maxOfAreas);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2468().solution();
    }
}
