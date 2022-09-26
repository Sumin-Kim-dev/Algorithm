import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3020 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] walls = new int[n];
        for (int i = 0; i < n; i++) {
            walls[i] = Integer.parseInt(br.readLine());
        }
        int[] solution = solution(n, h, walls);
        System.out.println(solution[0] + " " + solution[1]);
    }

    int[] solution(int n, int h, int[] walls) {
        int[] top = setTop(n, walls);
        int[] bottom = setBottom(n, h, walls);

        ArrayList<int[]> topBreak = setBreak(top);
        ArrayList<int[]> bottomBreak = setBreak(bottom);

        int min = n, count = 0;
        for (int i = 1; i <= h; i++) {
            int curr = topBreak(i, topBreak) + bottomBreak(i, bottomBreak);
            if (curr == min) count++;
            else if (curr < min) {
                min = curr;
                count = 1;
            }
        }
        return new int[]{min, count};
    }

    int[] setTop(int n, int[] walls) {
        int[] top = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            top[i] = walls[2 * i];
        }
        return Arrays.stream(top).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(i -> i).toArray();
    }

    int[] setBottom(int n, int h, int[] walls) {
        int[] bottom = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            bottom[i] = walls[2 * i + 1];
        }
        return Arrays.stream(bottom)
                .map(i -> h - i + 1)
                .sorted().toArray();
    }

    ArrayList<int[]> setBreak(int[] walls) {
        ArrayList<int[]> breakList = new ArrayList<>();
        breakList.add(new int[]{walls[0], 1});
        for (int i = 1; i < walls.length; i++) {
            int[] currTop = breakList.get(breakList.size() - 1);
            if (walls[i] == currTop[0]) {
                currTop[1]++;
            } else {
                breakList.add(new int[]{walls[i], currTop[1] + 1});
            }
        }
        return breakList;
    }

    int topBreak(int i, ArrayList<int[]> topBreak) {
        if (i > topBreak.get(0)[0]) return 0;
        int start = 0;
        int end = topBreak.size();
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (i <= topBreak.get(mid)[0]) start = mid;
            else end = mid;
        }
        return topBreak.get(start)[1];
    }

    int bottomBreak(int i, ArrayList<int[]> bottomBreak) {
        if (i < bottomBreak.get(0)[0]) return 0;
        int start = 0;
        int end = bottomBreak.size();
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (i >= bottomBreak.get(mid)[0]) start = mid;
            else end = mid;
        }
        return bottomBreak.get(start)[1];
    }

    public static void main(String[] args) throws IOException {
        new BOJ3020().io();
    }
}
