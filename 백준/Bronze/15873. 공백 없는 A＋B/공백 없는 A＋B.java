import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int answer = 0;
        if (str.length == 2) {
            answer = str[0] - '0' + str[1] - '0';
        } else if (str.length == 3) {
            answer = str[0] - '0' + str[1] - '0' + str[2] - '0' + 9;
        } else {
            answer = 20;
        }
        System.out.println(answer);
    }
}