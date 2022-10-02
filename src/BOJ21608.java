import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21608 {
    final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] like = new int[n * n][5];
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                like[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(n, like));
    }

    int solution(int n, int[][] like) {
        int[][] students = setStudents(n, like);
        int score = 0;
        for (int i = 0; i < n * n; i++) {
            int currScore = 0;
            for (int[] dir : DIRECTION) {
                int nextI = students[like[i][0]][0] + dir[0];
                int nextJ = students[like[i][0]][1] + dir[1];
                if (!isValid(nextI, nextJ, n)) continue;
                for (int m = 1; m <= 4; m++) {
                    if (students[like[i][m]][0] == nextI && students[like[i][m]][1] == nextJ) {
                        currScore *= 10;
                        if (currScore == 0) currScore++;
                    }
                }
            }
            score += currScore;
        }
        return score;
    }

    int[][] setStudents(int n, int[][] like) {
        int[][] curr = new int[n][n];
        int[][] students = new int[n * n + 1][2];
        curr[1][1] = like[0][0];
        students[like[0][0]] = new int[]{1, 1};
        for (int k = 1; k < n * n; k++) {
            Chair min = new Chair(n, n, 0, 0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (curr[i][j] != 0) continue;
                    int likeAdj = 0;
                    int emptyAdj = 0;
                    for (int[] dir : DIRECTION) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (!isValid(nextI, nextJ, n)) continue;
                        if (curr[nextI][nextJ] == 0) emptyAdj++;
                        for (int m = 1; m <= 4; m++) {
                            if (curr[nextI][nextJ] == like[k][m]) likeAdj++;
                        }
                    }
                    Chair temp = new Chair(i, j, likeAdj, emptyAdj);
                    if (temp.compareTo(min) < 0) {
                        min = temp;
                    }
                }
            }
            curr[min.i][min.j] = like[k][0];
            students[like[k][0]] = new int[]{min.i, min.j};
        }
        return students;
    }

    boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    static class Chair implements Comparable<Chair> {
        int i, j;
        int likeAdj, emptyAdj;

        Chair(int i, int j, int likeAdj, int emptyAdj) {
            this.i = i;
            this.j = j;
            this.likeAdj = likeAdj;
            this.emptyAdj = emptyAdj;
        }

        @Override
        public int compareTo(Chair c) {
            if (likeAdj != c.likeAdj) return c.likeAdj - likeAdj;
            if (emptyAdj != c.emptyAdj) return c.emptyAdj - emptyAdj;
            if (i != c.i) return i - c.i;
            return j - c.j;
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ21608().io();
    }
}
