import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1958 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();
        System.out.println(solution(s1, s2, s3));
    }

    int solution(String s1, String s2, String s3) {
        int[][][] lcs = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 1; k <= s3.length(); k++) {
                    lcs[i][j][k] = Math.max(lcs[i][j][k - 1], Math.max(lcs[i][j - 1][k], lcs[i - 1][j][k]));
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1))
                        lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
                }
            }
        }
        return lcs[s1.length()][s2.length()][s3.length()];
    }

    public static void main(String[] args) throws IOException {
        new BOJ1958().io();
    }
}
