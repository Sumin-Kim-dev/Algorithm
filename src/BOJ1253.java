import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1253 {
    public static void main(String[] args) throws IOException {
        new BOJ1253().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(n, a));
    }

    int solution(int n, int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int sum = a[left] + a[right];
                if (a[i] == sum) {
                    if (left != i && right != i) {
                        count++;
                        break;
                    }
                    if (left == i) left++;
                    else right--;
                } else if (sum < a[i]) left++;
                else right--;
            }
        }
        return count;
    }
}
