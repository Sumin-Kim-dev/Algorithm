import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25214 {
    int n, ans, minIndex, currI, currJ;
    int[] arr;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        StringBuilder sb = new StringBuilder();
        ans = minIndex = currI = currJ = 0;
        for (int j = 0; j < n; j++) {
            arr[j] = Integer.parseInt(st.nextToken());
            if (arr[j] < arr[minIndex]) minIndex = j;
            if (arr[j] - arr[minIndex] > ans) {
                currJ = j;
                currI = minIndex;
                ans = arr[currJ] - arr[currI];
            }
            sb.append(ans).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new BOJ25214().solution();
    }
}
