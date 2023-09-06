import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] sum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        int midIndex = 0;
        for (int i = 0; i < n; i++) {
        	int curr = Integer.parseInt(st.nextToken());
        	if (curr < b) sum[i + 1] = sum[i] - 1;
        	else if (curr > b) sum[i + 1] = sum[i] + 1;
        	else {
        		sum[i + 1] = sum[i];
        		midIndex = i + 1;
        	}
        }
        int[][] set = new int[2 * n + 1][2];
        for (int i = 0; i <= n; i++) {
        	if (i < midIndex) set[sum[i] + n][0]++;
        	else if (i >= midIndex) set[sum[i] + n][1]++;
        }
        int answer = 0;
        for (int i = 0; i <= 2 * n; i++) {
        	answer += set[i][0] * set[i][1];
        }
        System.out.println(answer);
    }
}