import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ18428 {
    int n;
    int[][] hall;
    Set<int[]> students;

    public static void main(String[] args) throws IOException {
        new BOJ18428().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        hall = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                char c = st.nextToken().charAt(0);
                if (c == 'T') hall[i][j] = 1;
                else if (c == 'S') hall[i][j] = 2;
            }
        }
        System.out.println(solution() ? "YES" : "NO");
    }

    boolean solution() {
        students = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hall[i][j] == 1) students.add(new int[]{i, j});
            }
        }
        return backtracking(0, 0);
    }

    boolean backtracking(int depth, int start) {
        if (success()) return true;
        if (depth == 3) return false;
        for (int i = start; i < n * n; i++) {
            if (hall[i / n][i % n] > 0) continue;
            hall[i / n][i % n] = -1;
            if (backtracking(depth + 1, i + 1)) return true;
            hall[i / n][i % n] = 0;
        }
        return false;
    }

    boolean success() {
        for (int[] pos : students) {
            for (int i = pos[0] - 1; i >= 0; i--) {
                if (hall[i][pos[1]] == -1) break;
                if (hall[i][pos[1]] == 2) return false;
            }
            for (int i = pos[0] + 1; i < n; i++) {
                if (hall[i][pos[1]] == -1) break;
                if (hall[i][pos[1]] == 2) return false;
            }
            for (int j = pos[1] - 1; j >= 0; j--) {
                if (hall[pos[0]][j] == -1) break;
                if (hall[pos[0]][j] == 2) return false;
            }
            for (int j = pos[1] + 1; j < n; j++) {
                if (hall[pos[0]][j] == -1) break;
                if (hall[pos[0]][j] == 2) return false;
            }
        }
        return true;
    }
}
