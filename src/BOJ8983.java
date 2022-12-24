import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8983 {
    public static void main(String[] args) throws IOException {
        new BOJ8983().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] guns = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            guns[i] = Integer.parseInt(st.nextToken());
        }
        int[][] animals = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(l, guns, animals));
    }

    int solution(int l, int[] guns, int[][] animals) {
        int count = 0;
        Arrays.sort(guns);
        for (int[] animal : animals) {
            if (dist(guns, animal) <= l) count++;
        }
        return count;
    }

    int dist(int[] guns, int[] animal) {
        int start = 0;
        int end = guns.length;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (guns[mid] < animal[0]) start = mid;
            else end = mid;
        }
        int xDist = Math.abs(animal[0] - guns[start]);
        if (end != guns.length) xDist = Math.min(xDist, guns[end] - animal[0]);
        return xDist + animal[1];
    }
}
