import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String num = st.nextToken();
        int base = Integer.parseInt(st.nextToken());
        int answer = 0;
        for (int i = 0; i < num.length(); i++) {
            int c = (int) num.charAt(i);
            answer *= base;
            if (c >= '0' && c <= '9') answer += (c - '0');
            else answer += (c - 'A') + 10;
        }
        System.out.println(answer);
    }
}