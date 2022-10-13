import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17143 {
    int r, c, m;
    Shark[] sharks;
    int[][] field;

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sharks = new Shark[m];
        field = new int[r][c];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            if (d < 2) s %= (2 * this.r - 2);
            else s %= (2 * this.c - 2);
            sharks[i] = new Shark(r, c, s, d, z);
            field[sharks[i].r][sharks[i].c] = i + 1;
        }
        System.out.println(solution());
    }

    int solution() {
        int answer = 0;
        for (int i = 0; i < c; i++) {
            int curr = 0;
            while (curr < r && field[curr][i] == 0) {
                curr++;
            }
            if (curr < r) {
                answer += sharks[field[curr][i] - 1].z;
                sharks[field[curr][i] - 1].z = 0;
            }
            move();
            eat();
        }
        return answer;
    }

    void move() {
        for (int i = 0; i < m; i++) {
            if (sharks[i].z == 0) continue;
            field[sharks[i].r][sharks[i].c] = 0;
            switch (sharks[i].d) {
                case 0: {
                    moveUp(sharks[i]);
                    break;
                }
                case 1: {
                    moveDown(sharks[i]);
                    break;
                }
                case 2: {
                    moveRight(sharks[i]);
                    break;
                }
                case 3: {
                    moveLeft(sharks[i]);
                    break;
                }
            }
        }
    }

    void eat() {
        for (int i = 0; i < m; i++) {
            if (sharks[i].z == 0) continue;
            int curr = field[sharks[i].r][sharks[i].c] - 1;
            if (curr < 0) {
                field[sharks[i].r][sharks[i].c] = i + 1;
                continue;
            }
            if (sharks[curr].z > sharks[i].z) {
                sharks[i].z = 0;
            } else {
                field[sharks[i].r][sharks[i].c] = i + 1;
                sharks[curr].z = 0;
            }
        }
    }

    void moveUp(Shark shark) {
        shark.r -= shark.s;
        if (shark.r >= 0) return;
        shark.r = -shark.r;
        if (shark.r < r) {
            shark.d = 1;
            return;
        }
        shark.r = 2 * (r - 1) - shark.r;
    }

    void moveDown(Shark shark) {
        shark.r += shark.s;
        if (shark.r < r) return;
        shark.r = 2 * (r - 1) - shark.r;
        if (shark.r >= 0) {
            shark.d = 0;
            return;
        }
        shark.r = -shark.r;
    }

    void moveLeft(Shark shark) {
        shark.c -= shark.s;
        if (shark.c >= 0) return;
        shark.c = -shark.c;
        if (shark.c < c) {
            shark.d = 2;
            return;
        }
        shark.c = 2 * (c - 1) - shark.c;
    }

    void moveRight(Shark shark) {
        shark.c += shark.s;
        if (shark.c < c) return;
        shark.c = 2 * (c - 1) - shark.c;
        if (shark.c >= 0) {
            shark.d = 3;
            return;
        }
        shark.c = -shark.c;
    }

    public static void main(String[] args) throws IOException {
        new BOJ17143().io();
    }
}
