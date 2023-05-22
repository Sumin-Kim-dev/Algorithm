import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int cents = Integer.parseInt(br.readLine());
            sb.append(cents / 25).append(' ');
            cents %= 25;
            sb.append(cents / 10).append(' ');
            cents %= 10;
            sb.append(cents / 5).append(' ');
            cents %= 5;
            sb.append(cents).append('\n');
        }
        System.out.println(sb);
    }
}