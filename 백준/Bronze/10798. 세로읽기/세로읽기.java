import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rows = new String[5];
        int[] lengths = new int[5];
        for (int i = 0; i < 5; i++) {
            rows[i] = br.readLine();
            lengths[i] = rows[i].length();
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 5; i++) {
                if (j >= lengths[i]) continue;
                sb.append(rows[i].charAt(j));
            }
        }
        System.out.println(sb);
    }
}