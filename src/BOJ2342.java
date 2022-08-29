import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2342 {
    final int FIRST = 2, REPEAT = 1, MOVE_ADJ = 3, MOVE_OPP = 4, INF = 500000;
    ArrayList<Integer> command;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int curr;
        command = new ArrayList<>();
        while (true) {
            curr = Integer.parseInt(st.nextToken());
            if (curr == 0) break;
            command.add(curr);
        }
    }

    int dp() {
        if (command.isEmpty()) return 0;
        int[] ans = new int[5]; // 한발(A)은 마지막 위치, 다른 발(B)의 위치는 index
        ans[0] = FIRST;
        for (int i = 1; i <= 4; i++) {
            ans[i] = INF; // 첫번째 이동 때 다른 발은 무조건 원점에 있음
        }
        int curr, next, min;
        for (int i = 1; i < command.size(); i++) {
            min = Integer.MAX_VALUE; // B발이 마지막 위치로 이동할 때의 최소값 찾기
            curr = command.get(i - 1);
            next = command.get(i);
            if (curr == next) { // B발이 이동하는 것은 불가능. A발이 반복해서 눌러야 함
                for (int j = 0; j <= 4; j++) {
                    ans[j] += REPEAT;
                }
                continue;
            }
            for (int j = 0; j <= 4; j++) {
                if (j == next) {
                    if (min > ans[j] + REPEAT) {
                        min = ans[j] + REPEAT; // B발이 제자리에서 반복해서 누름
                    }
                    ans[j] = INF; // 두 발이 같이 놓일 수는 없음
                    continue;
                }
                if (min > ans[j] + move(j, next)) {
                    min = ans[j] + move(j, next);
                }
                ans[j] += move(curr, next);
            }
            ans[curr] = min;
        }
        return Arrays.stream(ans).min().getAsInt();
    }

    void solution() throws IOException {
        input();
        System.out.println(dp());
    }

    int move(int curr, int next) {
        if (curr == 0) return FIRST;
        if (curr == next) return REPEAT;
        if (curr % 2 == next % 2) return MOVE_OPP;
        return MOVE_ADJ;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2342().solution();
    }
}
