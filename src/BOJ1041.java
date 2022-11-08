import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1041 {
    long n;
    int[] dice = new int[6];
    final int MAX = 50;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    long solution() {
        if (n == 1) return five();
        long one = 5 * (n - 2) * (n - 2) + 4 * (n - 2);
        long two = 8 * (n - 2) + 4;
        long three = 4;
        return one * one() + two * two() + three * three();
    }

    int one() {
        int min = MAX;
        for (int i = 0; i < 6; i++) {
            if (dice[i] < min) {
                min = dice[i];
            }
        }
        return min;
    }

    int two() {
        int min = MAX * 2;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                if (i == 5 && j == 0 || i == 4 && j == 1 || i == 3 && j == 2) continue;
                if (dice[i] + dice[j] < min) {
                    min = dice[i] + dice[j];
                }
            }
        }
        return min;
    }

    int three() {
        int min = MAX * 3;
        HashSet<Integer> set = new HashSet<>();
        set.add(dice[0] + dice[1] + dice[2]);
        set.add(dice[0] + dice[1] + dice[3]);
        set.add(dice[0] + dice[4] + dice[2]);
        set.add(dice[0] + dice[4] + dice[3]);
        set.add(dice[5] + dice[1] + dice[2]);
        set.add(dice[5] + dice[1] + dice[3]);
        set.add(dice[5] + dice[4] + dice[2]);
        set.add(dice[5] + dice[4] + dice[3]);
        for (int curr : set) {
            if (curr < min) {
                min = curr;
            }
        }
        return min;
    }

    int five() {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < 6; i++) {
            sum += dice[i];
            if (max < dice[i]) {
                max = dice[i];
            }
        }
        return sum - max;
    }

    public static void main(String[] args) throws IOException {
        new BOJ1041().io();
    }
}
