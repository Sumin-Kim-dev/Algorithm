import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1034 {
    int n, k;
    HashMap<String, Integer> rows;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        rows = new HashMap<>();
        String row;
        for (int i = 0; i < n; i++) {
            row = br.readLine();
            rows.put(row, rows.getOrDefault(row, 0) + 1);
        }
        k = Integer.parseInt(br.readLine());
    }

    void solution() throws IOException {
        input();
        int[][] arr = new int[rows.size()][2];
        int index = 0;
        for (String key : rows.keySet()) {
            arr[index][0] = countZeros(key);
            arr[index][1] = rows.get(key);
            index++;
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> -o[1]));
        int ans = 0;
        for (int[] currRow : arr) {
            if (isAble(currRow[0])) {
                ans = currRow[1];
                break;
            }
        }
        System.out.println(ans);
    }

    int countZeros(String str) {
        int length = str.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '0') count++;
        }
        return count;
    }

    boolean isAble(int zeros) {
        return k >= zeros && (k - zeros) % 2 == 0;
    }

    public static void main(String[] args) throws IOException {
        new BOJ1034().solution();
    }
}
