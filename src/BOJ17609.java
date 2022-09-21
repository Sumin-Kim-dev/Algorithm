import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {
    int isPalindrome(String str) {
        return isPalindrome(str, 0, str.length() - 1);
    }

    int isPalindrome(String str, int i, int j) {
        if (i >= j) return 0;
        if (str.charAt(i) == str.charAt(j)) return isPalindrome(str, i + 1, j - 1);
        int isPalindrome = 2;
        if (str.charAt(i) == str.charAt(j - 1)) {
            isPalindrome = Math.min(isPalindrome, isPalindrome(str, i + 1, j - 2) + 1);
        }
        if (str.charAt(i + 1) == str.charAt(j)) {
            isPalindrome = Math.min(isPalindrome, isPalindrome(str, i + 2, j - 1) + 1);
        }
        return isPalindrome;
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String str = br.readLine();
            System.out.println(isPalindrome(str));
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ17609().solution();
    }
}
