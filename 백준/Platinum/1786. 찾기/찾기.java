import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String p = br.readLine();
        int[] fail = fail(p);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int i = 0;
        while (start < t.length()) {
            if (t.charAt(start) == p.charAt(i)) {
                start++;
                i++;
                if (i == p.length()) {
                    count++;
                    sb.append(start - i + 1).append(' ');
                    i = fail[i - 1];
                }
            } else if (i > 0) {
                i = fail[i - 1];
            } else {
                start++;
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
    
    static int[] fail(String p) {
        int[] fail = new int[p.length()];
        int end = 1;
        int f = 0;
        while (end < p.length()) {
            if (p.charAt(end) == p.charAt(f)) {
                fail[end++] = ++f;
            } else if (f > 0) {
                f = fail[f - 1];
            } else {
                fail[end++] = 0;
            }
        }
        return fail;
    }
}