import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5582 {
    public static void main(String[] args) throws IOException {
        new BOJ5582().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        System.out.println(lcs(str1, str2));
    }

    int lcs(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        int[][] common = new int[length1 + 1][length2 + 1];
        int lcs = 0;
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    common[i][j] = common[i - 1][j - 1] + 1;
                    if (lcs < common[i][j]) lcs = common[i][j];
                }
            }
        }
        return lcs;
    }
}
