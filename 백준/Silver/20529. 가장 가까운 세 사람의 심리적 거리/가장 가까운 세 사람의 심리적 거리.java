import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] types = new int[1 << 4];
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (n--  > 0) {
                types[type(st.nextToken())]++;
            }
            sb.append(answer(n, types)).append('\n');
        }
        System.out.println(sb);
    }
    
    private static int type(String mbti) {
        int type = 0;
        if (mbti.charAt(0) == 'I') type |= 1 << 0;
        if (mbti.charAt(1) == 'S') type |= 1 << 1;
        if (mbti.charAt(2) == 'T') type |= 1 << 2;
        if (mbti.charAt(3) == 'J') type |= 1 << 3;
        return type;
    }
    
    private static int answer(int n, int[] types) {
        int minDist = 8;
        for (int i = 0; i < 16; i++) {
            if (types[i] <= 0) continue;
            types[i]--;
            for (int j = i; j < 16; j++) {
                if (types[j] <= 0) continue;
                types[j]--;
                for (int k = j; k < 16; k++) {
                    if (types[k] <= 0) continue;
                    int dist = 0;
                    for (int b = 1; b < (1 << 4); b *= 2) {
                    	if ((i & b) == (j & b) && (j & b) == (k & b)) continue;
                    	dist += 2;
                    }
                    if (dist < minDist) minDist = dist;
                }
                types[j]++;
            }
            types[i]++;
        }
        return minDist;
    }
}