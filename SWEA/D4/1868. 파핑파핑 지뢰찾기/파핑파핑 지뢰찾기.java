import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class Solution {
     
    static int n;
    static int[][] map;
    static int[][] number;
    static int[][] deltas = {{-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, -1, 1, -1, 0, 1}};
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine();
                for (int j = 0; j < n; j++) {
                    if (row.charAt(j) == '*') {
                        map[i][j] = 1;
                    }
                }
            }
            sb.append(answer()).append("\n");
        }
        System.out.println(sb);
    }
 
    private static int answer() {
        number();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (number[i][j] == 0) {
                    click(i, j);
                    count++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (number[i][j] > 0) count++;
            }
        }
        return count;
    }
     
    private static void click(int i, int j) {
        number[i][j] = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 8; d++) {
                int[] next = {curr[0] + deltas[0][d], curr[1] + deltas[1][d]};
                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= n) continue;
                if (number[next[0]][next[1]] == 0) {
                    queue.offer(next);
                }
                number[next[0]][next[1]] = -1;
            }
        }
    }
 
    private static void number() {
        number = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    number[i][j] = -1;
                    continue;
                }
                for (int d = 0; d < 8; d++) {
                    int currI = i + deltas[0][d];
                    int currJ = j + deltas[1][d];
                    if (currI < 0 || currI >= n || currJ < 0 || currJ >= n) continue;
                    if (map[currI][currJ] == 1) number[i][j]++;
                }
            }
        }
    }
}