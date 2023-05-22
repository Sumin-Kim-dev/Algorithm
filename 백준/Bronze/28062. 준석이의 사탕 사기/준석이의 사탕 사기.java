import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int minOdd = 1001;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int curr = Integer.parseInt(st.nextToken());
            if (curr % 2 == 1 && curr < minOdd) minOdd = curr;
            sum += curr;
        }
        if (sum % 2 == 0) System.out.println(sum);
        else System.out.println(sum - minOdd);
    }
}