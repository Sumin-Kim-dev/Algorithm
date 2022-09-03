import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2618 {
    int n, w;
    int[][] points1, points2;
    Path[][] paths;

    static class Path {
        final int[] before;
        final int minDist;

        Path(int[] before, int minDist) {
            this.before = before;
            this.minDist = minDist;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        points1 = new int[w + 1][];
        points2 = new int[w + 1][];
        points1[0] = new int[]{1, 1};
        points2[0] = new int[]{n, n};
        StringTokenizer st;
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            points1[i + 1] = points2[i + 1] =
                    new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        paths = new Path[w + 1][w + 1];
    }

    int dist(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    Path getPath(int i, int j) {
        // paths[i][j] : 1번 경찰차의 현 위치 i, 2번 경찰차의 현 위치 j
        // 초기 상태
        if (i == 0 && j == 0)
            return paths[i][j] = new Path(new int[]{0, 0}, 0);
        if (paths[i][j] != null)
            return paths[i][j];
        // 마지막 이동 : 1번 i - 1 -> i
        if (j < i - 1) {
            int minDist = getPath(i - 1, j).minDist + dist(points1[i], points1[i - 1]);
            return paths[i][j] = new Path(new int[]{i - 1, j}, minDist);
        }
        // 마지막 이동 : 2번 j - 1 -> j
        if (i < j - 1) {
            int minDist = getPath(i, j - 1).minDist + dist(points2[j], points2[j - 1]);
            return paths[i][j] = new Path(new int[]{i, j - 1}, minDist);
        }
        // 마지막 이동 : 1번 ? -> i
        if (j == i - 1) {
            int minDist = getPath(0, j).minDist + dist(points1[i], points1[0]);
            int minIndex = 0;
            for (int k = 1; k < j; k++) {
                int dist = getPath(k, j).minDist + dist(points1[i], points1[k]);
                if (dist < minDist) {
                    minDist = dist;
                    minIndex = k;
                }
            }
            return paths[i][j] = new Path(new int[]{minIndex, j}, minDist);
        }
        // 마지막 이동 : 2번 ? -> j
        if (i == j - 1) {
            int minDist = getPath(i, 0).minDist + dist(points2[j], points2[0]);
            int minIndex = 0;
            for (int k = 1; k < i; k++) {
                int dist = getPath(i, k).minDist + dist(points2[j], points2[k]);
                if (dist < minDist) {
                    minDist = dist;
                    minIndex = k;
                }
            }
            return paths[i][j] = new Path(new int[]{i, minIndex}, minDist);
        } else return null;
    }

    int[] finalPostion() {
        int minDist = Integer.MAX_VALUE;
        int[] position = new int[2];
        for (int i = 0; i < w; i++) {
            int dist = getPath(i, w).minDist;
            if (dist < minDist) {
                minDist = dist;
                position[0] = i;
                position[1] = w;
            }
            dist = getPath(w, i).minDist;
            if (dist < minDist) {
                minDist = dist;
                position[0] = w;
                position[1] = i;
            }
        }
        return position;
    }

    void traceback(int[] position) {
        System.out.println(paths[position[0]][position[1]].minDist);
        Stack<Integer> police = new Stack<>();
        for (int i = w; i > 0; i--) {
            if (position[0] == i) police.push(1);
            else police.push(2);
            position = getPath(position[0], position[1]).before;
        }
        while (!police.isEmpty())
            System.out.println(police.pop());
    }

    void solution() throws IOException {
        input();
        traceback(finalPostion());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2618().solution();
    }
}
