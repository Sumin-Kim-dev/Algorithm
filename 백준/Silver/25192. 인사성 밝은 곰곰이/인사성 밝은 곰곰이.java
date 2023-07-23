import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> names = new HashSet<>();
        int count = 0;
        while (n-- > 0) {
            String str = br.readLine();
            if (str.equals("ENTER")) names = new HashSet<>();
            else if (names.contains(str)) continue;
            else {
                names.add(str);
                count++;
            }
        }
        System.out.println(count);
    }
}