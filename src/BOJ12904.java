import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904 {
    String s, t;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();
    }

    boolean isAble(String s, String t) {
        while (t.length() > s.length()) {
            boolean reverse = t.charAt(t.length() - 1) == 'B';
            t = t.substring(0, t.length() - 1);
            if (reverse) t = reverse(t);
        }
        return t.equals(s);
    }

    String reverse(String str) {
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = str.charAt(chars.length - 1 - i);
        }
        return String.valueOf(chars);
    }

    void solution() throws IOException {
        input();
        System.out.println(isAble(s, t) ? 1 : 0);
    }

    public static void main(String[] args) throws IOException {
        new BOJ12904().solution();
    }
}
