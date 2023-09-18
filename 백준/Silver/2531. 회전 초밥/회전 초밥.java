import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken()) - 1;
        
        int[] arr = new int[n + k - 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
            if (i < k - 1) arr[i + n] = arr[i];
        }
        
        int[] sum = new int[d];
        int curr = 0;
        int max = 0;
        for (int i = 0; i < k; i++) {
            if (sum[arr[i]] == 0) curr++;
            sum[arr[i]]++;
        }
        max = sum[c] == 0 ? curr + 1 : curr;
        for (int i = 0; i < n - 1; i++) {
            sum[arr[i]]--;
            if (sum[arr[i]] == 0) curr--;
            if (sum[arr[i + k]] == 0) curr++;
            sum[arr[i + k]]++;
            int add = sum[c] == 0 ? 1 : 0;
            if (max < curr + add) max = curr + add;
        }
        System.out.println(max);
    }
}