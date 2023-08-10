import java.io.*;
import java.util.*;

public class Main {

    static int N, points[][], tmp, ans;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        points = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                points[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //풀이 시작
        visit = new boolean[9];
        baseball(0, new int[9]);
        
        bw.write(ans+"\n");
        bw.close();
    }

    static void baseball(int n, int[] seq) {
        if (n == 3) {    // 4번째 차례에 1번타자를 배치하기 위함
            baseball(n + 1, seq);
            return;
        }

        if (n == 9) {    // 주자 순서 정하는 8! 배열 완성
            //N이닝마다 game객체 생성 -> base, score, out 초기화 / 주자번호와 점수합계는 유지
            tmp = 0;    
            int runner = 0;
            for (int i = 0; i < N; i++) {
                Game game = new Game();
                while (!game.isEnd()) {
                    game.hit(points[i][seq[runner++%9]]);
                }
                tmp += game.score;
            }
            // 8!의 배열중 최대값 저장
            ans = Math.max(ans, tmp);
            return;
        }
        
        // 순열
        for (int i = 1; i < 9; i++) {
            if (visit[i])
                continue;
            visit[i] = true;
            seq[n] = i;
            baseball(n + 1, seq);
            visit[i] = false;
        }
    }
}

class Game {
    int score;
    int out;
    boolean[] base = new boolean[3];    //1,2,3루에 주자가 있으면 true

    boolean isEnd() {    // 아웃카운트 확인
        if (out < 3) {
            return false;
        } else {
            return true;
        }
    }

    void hit(int n) {
        if (n==0) {        // 아웃
            out++;
            return;
        }else if(n==4) {    //홈런
            score++;
            for (int i = 0; i <= 2; i++) {
                if (base[i]) {
                    base[i] = false;
                    score++;
                }
            }
            return;
        }
        for (int i = 2; i > 2-n; i--) {        // 득점 베이스에 있는 주자 득점
            if (base[i]) {
                score++;
                base[i] = false;
            }
        }
        for (int i = 2-n; i >= 0; i--) {    // 미득점 진루
            if (base[i]) {
                base[i] = false;
                base[i+n] = true;
            }
        }
        base[n-1] = true;        // 타자 진루
    }
    
}