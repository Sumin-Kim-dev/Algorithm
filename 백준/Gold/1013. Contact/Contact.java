import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    boolean pattern(String s) {
        int i = 0;
        while (true) {
            if (i >= s.length()) return true;
            // 01 인 경우
            if (s.charAt(i++) == '0') {
                if (i == s.length() || s.charAt(i++) == '0') return false; // 00 꼴
                if (i == s.length()) return true; // 끝난 경우
            } else { // 100+1+ 인 경우
                if (i == s.length() || s.charAt(i++) == '1') return false; // 11.. 꼴
                if (i == s.length() || s.charAt(i++) == '1') return false; // 101.. 꼴
                while (i < s.length() && s.charAt(i) == '0') { // 100+ 통과
                    i++; // 1 시작되는 지점으로 이동
                }
                if (i == s.length()) return false; // 100+ 꼴
                while (i < s.length() && s.charAt(i) == '1') { // 100+1+ 꼴
                    i++;
                } // 100+1+ 꼴이 끝난 후 처음 나오는 0
                if (i == s.length()) return true; // 끝난 경우
                if (i == s.length() - 1) return false; // ..1/0 or ..1/10 꼴
                if (s.charAt(i + 1) == '0') { // ../100+ 꼴. i를 1쪽으로 옮겨줌
                    i--;
                    if (s.charAt(i - 1) == '0') return false; // ..0/100+ 꼴
                } else i += 2; // ..1/01 꼴. i를 01 끝난 후로 옮겨줌
            }
        }
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String s = br.readLine();
            System.out.println(pattern(s) ? "YES" : "NO");
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
