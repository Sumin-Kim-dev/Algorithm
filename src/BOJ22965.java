import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ22965 {
    int n;
    int[] a;

    public static void main(String[] args) throws IOException {
        new BOJ22965().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    int solution() {
        int[] sortedA = Arrays.stream(a).sorted().toArray();
        int diff = Arrays.binarySearch(sortedA, a[0]);
        boolean circle = true;
        for (int i = 0; i < n; i++) {
            circle &= (a[i] == sortedA[(i + diff) % n]);
        }
        if (circle) return diff > 0 ? 2 : 1;
        return 3;
    }
}
