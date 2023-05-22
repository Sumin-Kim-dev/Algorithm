import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int curr = Integer.parseInt(st.nextToken()) - (n + 1 - i);
            if (curr > max) max = curr;
        }
        System.out.println(max);
    }
}