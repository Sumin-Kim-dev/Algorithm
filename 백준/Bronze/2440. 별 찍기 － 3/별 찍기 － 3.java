import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            sb.append("*".repeat(i)).append('\n');
        }
        System.out.println(sb);
    }
}