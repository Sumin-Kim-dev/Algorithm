import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] count = new int[4];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch(c) {
                case 'U': 
                    count[0]++;
                    break;
                case 'D': 
                    count[1]++;
                    break;
                case 'P': 
                    count[2]++;
                    break;
                case 'C':
                    count[3]++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (count[0] + count[3] > (count[1] + count[2] + 1) / 2) {
            sb.append('U');
        }
        if (count[1] > 0 || count[2] > 0) {
            sb.append("DP");
        }
        System.out.println(sb);
    }
}