import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        while (br.readLine() != null) {
            count++;
        }
        System.out.println(count);
    }
}