import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int burger = 2001;
        for (int i = 0; i < 3; i++) {
            int curr = Integer.parseInt(br.readLine());
            if (curr < burger) burger = curr;
        }
        int drink = 2001;
        for (int i = 0; i < 2; i++) {
            int curr = Integer.parseInt(br.readLine());
            if (curr < drink) drink = curr;
        }
        System.out.println(burger + drink - 50);
    }
}