import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cut = (int) (n * 0.15 + 0.5);
        int level = 0;
        int[] suggest = new int[n];
        for (int i = 0; i < n; i++) {
            suggest[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(suggest);
        for (int i = cut; i < n - cut; i++) {
            level += suggest[i];
        }
        if (n > 0) {
            level = (int) (level * 1.0 / (n - 2 * cut) + 0.5);
        }
        System.out.println(level);
    }
}