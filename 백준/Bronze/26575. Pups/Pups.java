import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double dog = Double.parseDouble(st.nextToken());
            double food = Double.parseDouble(st.nextToken());
            double price = Double.parseDouble(st.nextToken());
            sb.append(String.format("$%.2f", dog * food * price)).append("\n");
        }
        System.out.println(sb);
    }
}