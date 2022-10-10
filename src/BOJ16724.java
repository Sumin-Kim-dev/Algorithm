import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724 {
    int n, m;
    char[][] map;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        System.out.println(solution(n, m, map));
    }

    int[][] isVisited;

    int solution(int n, int m, char[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
        isVisited = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isVisited[i][j] > 0) continue;
                count = Math.max(count, check(i, j, count));
            }
        }
        return count;
    }

    int check(int i, int j, int count) {
        if (isVisited[i][j] > 0) return isVisited[i][j];
        int curr = count + 1;
        isVisited[i][j] = curr;
        switch (map[i][j]) {
            case 'U':
                curr = check(i - 1, j, count);
                break;
            case 'D':
                curr = check(i + 1, j, count);
                break;
            case 'L':
                curr = check(i, j - 1, count);
                break;
            case 'R':
                curr = check(i, j + 1, count);
                break;
        }
        return isVisited[i][j] = curr;
    }

    public static void main(String[] args) throws IOException {
        new BOJ16724().io();
    }
}
