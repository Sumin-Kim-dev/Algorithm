import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] coord = new boolean[100][100];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int minX = Integer.parseInt(st.nextToken());
            int minY = Integer.parseInt(st.nextToken());
            int maxX = Integer.parseInt(st.nextToken());
            int maxY = Integer.parseInt(st.nextToken());
            for (int j = minX; j < maxX; j++) {
                for (int k = minY; k < maxY; k++) {
                    coord[j][k] = true;
                }
            }
        }
        int count = 0;
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                if (coord[j][k]) count++;
            }
        }
        System.out.println(count);
    }
}