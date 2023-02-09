import java.io.*;
import java.util.*;
import java.util.Queue;

public class BOJ16946 {
    public static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int n, m;
    int[][] map;
    int[][] size;

    public static void main(String[] args) throws IOException {
        new BOJ16946().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        size = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        print(solution());
    }

    int[][] solution() {
        setSize();
        int[][] result = new int[n][m];
        int[] set = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                result[i][j] = 1;
                for (int k = 0; k < 4; k++) {
                    set[k] = 0;
                    int nextI = i + DIR[k][0];
                    int nextJ = j + DIR[k][1];
                    if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                    if (size[nextI][nextJ] == 0) continue;
                    if (size[nextI][nextJ] < 0) {
                        set[k] = -size[nextI][nextJ];
                    } else {
                        set[k] = nextI * m + nextJ + 1;
                    }
                }
                Arrays.sort(set);
                for (int k = 0; k < 4; k++) {
                    if (set[k] == 0 || k > 0 && set[k] == set[k - 1]) continue;
                    result[i][j] += size[(set[k] - 1) / m][(set[k] - 1) % m];
                }
                result[i][j] %= 10;
            }
        }
        return result;
    }

    void setSize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (size[i][j] != 0) continue;
                if (map[i][j] == 1) continue;
                bfs(i, j);
            }
        }
    }

    void bfs(int startI, int startJ) {
        int start = startI * m + startJ + 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        size[startI][startJ] = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll() - 1;
            int currI = curr / m;
            int currJ = curr % m;
            for (int[] dir : DIR) {
                int nextI = currI + dir[0];
                int nextJ = currJ + dir[1];
                if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;
                if (map[nextI][nextJ] == 1) continue;
                if (size[nextI][nextJ] != 0) continue;
                size[nextI][nextJ] = -start;
                size[startI][startJ]++;
                queue.offer(nextI * m + nextJ + 1);
            }
        }
    }

    void print(int[][] arr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int[] row : arr) {
            for (int num : row) {
                bw.write(num + '0');
            }
            bw.newLine();
        }
        bw.close();
    }
}
