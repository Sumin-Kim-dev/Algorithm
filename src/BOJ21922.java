import java.io.*;
import java.util.*;

public class BOJ21922 {
    public static void main(String[] args) throws IOException {
        new BOJ21922().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] lab = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, m, lab));
    }

    private int solution(int n, int m, int[][] lab) {
        // 4방향
        int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 물건을 만났을 때 바람이 바뀌는 방향 (0번 인덱스 : 물건이 없는 경우)
        // 방향이 4 : 더 이상 전진하지 못하고 막힌 경우
        int[][] map = {{0, 1, 2, 3}, {0, 1, 4, 4}, {4, 4, 2, 3}, {3, 2, 1, 0}, {2, 3, 0, 1}};
        List<int[]> startPoints = find(lab);
        boolean[][][] isVisited = new boolean[n][m][5];
        for (int[] start : startPoints) {
            for (int i = 0; i < 4; i++) {
                int currX = start[0];
                int currY = start[1];
                int currDir = i;
                isVisited[currX][currY][currDir] = true;
                while (currDir != 4) {
                    currX += DIR[currDir][0];
                    currY += DIR[currDir][1];
                    if (currX < 0 || currX >= n || currY < 0 || currY >= m) break;
                    currDir = map[lab[currX][currY]][currDir];
                    if (isVisited[currX][currY][currDir]) break;
                    isVisited[currX][currY][currDir] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 5; k++) {
                    if (isVisited[i][j][k]) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    private List<int[]> find(int[][] lab) {
        List<int[]> air = new ArrayList<>();
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if (lab[i][j] == 9) {
                    lab[i][j] = 0;
                    air.add(new int[]{i, j});
                }
            }
        }
        return air;
    }
}