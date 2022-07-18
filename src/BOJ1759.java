import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {
    int l, c;
    char[] letters;
    char[] code;
    int vowel = 0;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        letters = new char[c];
        code = new char[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++)
            letters[i] = st.nextToken().charAt(0);
        Arrays.sort(letters);
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    void printCode(int depth) throws IOException {
        if (depth == l) {
            if (vowel < 1 || vowel > l - 2) return;
            bw.write(code);
            bw.flush();
            bw.newLine();
            return;
        }
        for (char c : letters) {
            if (depth == 0 || code[depth - 1] < c) {
                if (isVowel(c)) vowel++;
                code[depth] = c;
                printCode(depth + 1);
                code[depth] = ' ';
                if (isVowel(c)) vowel--;
            }
        }
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    void solution() throws IOException {
        input();
        printCode(0);
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1759().solution();
    }
}
