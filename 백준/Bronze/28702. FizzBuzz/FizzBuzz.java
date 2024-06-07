import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = new String[3];
        strs[0] = br.readLine();
        strs[1] = br.readLine();
        strs[2] = br.readLine();
        int ans = 0;
        if (strs[1].equals("Fizz") && strs[2].equals("Buzz")
            || strs[1].equals("Buzz") && strs[2].equals("Fizz")
            || strs[2].equals("FizzBuzz")) {
            ans = Integer.parseInt(strs[0]) + 3;
        } else if (strs[1].equals("FizzBuzz")
                   || strs[1].equals("Fizz")
                   || strs[1].equals("Buzz")) {
            ans = Integer.parseInt(strs[2]) + 1;
        } else {
            ans = Integer.parseInt(strs[1]) + 2;
        }
        if (ans % 15 == 0) {
            System.out.println("FizzBuzz");
        } else if (ans % 3 == 0) {
            System.out.println("Fizz");
        } else if (ans % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(ans);
        }
    }
}