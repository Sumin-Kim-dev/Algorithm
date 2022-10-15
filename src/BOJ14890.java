import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
    int n, l;
    int[][] map;
    int[][] mapT;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        mapT = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                mapT[j][i] = map[i][j];
            }
        }
        System.out.println(solution());
    }

    int solution() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (able(map[i])) {
                count++;
                System.out.println(i + "행");
            }
            if (able(mapT[i])) {
                count++;
                System.out.println(i + "열");
            }
        }
        return count;
    }

    boolean able(int[] road) {
        int[] runway = new int[n];
        for (int j = 1; j < n; j++) {
            int curr = road[j];
            int diff = road[j - 1] - road[j];
            if (diff > 1 || diff < -1) return false;
            if (diff == 1) {
                if (j > n - l) return false;
                for (int k = 0; k < l; k++) {
                    if (curr != road[j]) return false;
                    runway[j] = k + 1;
                    j++;
                }
                j--;
            } else if (diff == -1) {
                if (j < l) return false;
                for (int k = 1; k <= l; k++) {
                    if (curr - 1 != road[j - k]) return false;
                    if (runway[j - k] > 0) return false;
                    runway[j - k] = k;
                }
            }
        }
        return runway[n - 1] == 0 || runway[n - 1] == l;
    }

    public static void main(String[] args) throws IOException {
        new BOJ14890().io();
    }
}
