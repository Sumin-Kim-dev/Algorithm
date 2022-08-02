import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9252 {
    String str1, str2;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
    }

    int[][] lcsLength() {
        int[][] lcsLength = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j))
                    lcsLength[i + 1][j + 1] = lcsLength[i][j] + 1;
                else lcsLength[i + 1][j + 1] = Math.max(lcsLength[i + 1][j], lcsLength[i][j + 1]);
            }
        }
        System.out.println(lcsLength[str1.length()][str2.length()]);
        return lcsLength;
    }

    void lcs(int[][] lcsLength) {
        StringBuilder sb = new StringBuilder();
        int i = str1.length();
        int j = str2.length();
        while (i > 0 && j > 0) {
            if (lcsLength[i][j] == lcsLength[i][j - 1]) {
                j--;
                continue;
            }
            if (lcsLength[i][j] == lcsLength[i - 1][j]) {
                i--;
                continue;
            }
            sb.insert(0, str1.charAt(i - 1));
            i--;
            j--;
        }
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        lcs(lcsLength());
    }

    public static void main(String[] args) throws IOException {
        new BOJ9252().solution();
    }
}
