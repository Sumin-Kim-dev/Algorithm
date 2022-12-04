import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2174 {
    static final int[][] dxdy = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int a, b;
    static int[][] ground;
    Robot[] robots;

    public static void main(String[] args) throws IOException {
        new BOJ2174().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        ground = new int[a][b];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            robots[i] = new Robot(i + 1, x, y, st.nextToken());
            ground[x][y] = i + 1;
        }
        boolean wrong = false;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken()) - 1;
            char cmd = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());
            if (cmd == 'F') {
                wrong = robots[no].F(repeat);
                if (wrong) break;
            }
            else if (cmd == 'L') robots[no].L(repeat);
            else robots[no].R(repeat);
        }
        if (!wrong) System.out.println("OK");
    }

    static class Robot {
        int no;
        int[] pos = new int[2];
        DIR dir;

        public Robot(int no, int x, int y, String dir) {
            this.no = no;
            pos[0] = x;
            pos[1] = y;
            this.dir = DIR.valueOf(dir);
        }

        boolean F(int repeat) {
            ground[pos[0]][pos[1]] = 0;
            for (int i = 0; i < repeat; i++) {
                pos[0] += dxdy[dir.ordinal()][0];
                pos[1] += dxdy[dir.ordinal()][1];
                if (pos[0] < 0 || pos[0] >= a || pos[1] < 0 || pos[1] >= b) {
                    System.out.printf("Robot %d crashes into the wall", no);
                    return true;
                }
                if (ground[pos[0]][pos[1]] > 0) {
                    System.out.printf("Robot %d crashes into robot %d", no, ground[pos[0]][pos[1]]);
                    return true;
                }
            }
            ground[pos[0]][pos[1]] = no;
            return false;
        }

        void L(int repeat) {
            int dirOrd = (dir.ordinal() + repeat) % 4;
            dir = DIR.values()[dirOrd];
        }

        void R(int repeat) {
            int dirOrd = (dir.ordinal() - repeat) % 4;
            if (dirOrd < 0) dirOrd += 4;
            dir = DIR.values()[dirOrd];
        }
    }

    enum DIR {
        N, W, S, E
    }
}
