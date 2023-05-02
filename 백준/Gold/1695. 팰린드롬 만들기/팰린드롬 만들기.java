import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    int[] arr;
    HashMap<Integer, Integer> count = new HashMap<>();

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    int dp() {
        // makePalindrm[i][j] : arr[i]~arr[j] 부분수열에 대한 답
        int[][] makePalindrom = new int[arr.length][arr.length];
        for (int length = 2; length <= arr.length; length++) {
            for (int i = length; i <= arr.length; i++) {
                if (arr[i - length] == arr[i - 1] && length > 2) {
                    makePalindrom[i - length][i - 1] = makePalindrom[i - length + 1][i - 2];
                }
                if (arr[i - length] != arr[i - 1]) {
                    makePalindrom[i - length][i - 1] = Math.min(makePalindrom[i - length][i - 2],
                            makePalindrom[i - length + 1][i - 1]) + 1;
                }
            }
        }
        return makePalindrom[0][arr.length - 1];
    }

    void solution() throws IOException {
        input();
        System.out.println(dp());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
