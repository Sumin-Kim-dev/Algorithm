import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long s = Long.parseLong(st.nextToken());
        long x = Long.parseLong(st.nextToken());
        long answer = 1L << Long.bitCount(x);
        if (s == x) answer -= 2;
        if (s < x) answer = 0;
        if ((s - x) % 2 != 0) answer = 0;
        if ((((s - x) / 2) & x) != 0) answer = 0;
        System.out.println(answer);
    }
}