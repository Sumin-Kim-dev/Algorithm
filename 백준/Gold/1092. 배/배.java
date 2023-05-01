import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] crain = new int[n];
        for (int i = 0; i < n; i++) {
            crain[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crain);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] box = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box);
        System.out.println(solution(n, crain, m, box));
    }

    int[] set;

    int solution(int n, int[] crain, int m, int[] box) {
        if (crain[n - 1] < box[m]) return -1;
        int[] max = new int[n];
        for (int i = 0; i < n; i++) {
            max[i] = binarySearch(crain[i], box);
        }
        int t = 0;
        set = new int[m + 1];
        Arrays.fill(set, -1);
        while (findSet(max[n - 1]) > 0) {
            for (int i = n - 1; i >= 0; i--) {
                max[i] = findSet(max[i]);
                union(max[i] - 1, max[i]);
            }
            t++;
        }
        return t;
    }

    int findSet(int a) {
        if (set[a] < 0) return a;
        return set[a] = findSet(set[a]);
    }

    void union(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int setB = findSet(b);
        set[setB] = a;
    }

    int binarySearch(int a, int[] box) {
        int start = 0;
        int end = box.length;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (box[mid] <= a) start = mid;
            else end = mid;
        }
        return start;
    }
}
