import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        boolean isPalindrome = true;
        int length = word.length();
        for (int i = 0; i < length / 2; i++) {
            isPalindrome &= word.charAt(i) == word.charAt(length - 1 - i);
        }
        System.out.println(isPalindrome ? 1 : 0);
    }
}