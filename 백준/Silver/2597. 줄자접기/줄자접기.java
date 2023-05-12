import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double start = 0;
        double end = Double.parseDouble(br.readLine());
        double[][] points = new double[3][2];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }
        for (int i = 0; i < 3; i++) {
            if (points[i][0] == points[i][1]) continue;
            double mid = (points[i][0] + points[i][1]) / 2;
            start = Math.min(start, 2 * mid - end);
            end = mid;
            for (int j = i + 1; j < 3; j++) {
                points[j][0] = points[j][0] > end ? 2 * end - points[j][0] : points[j][0];
                points[j][1] = points[j][1] > end ? 2 * end - points[j][1] : points[j][1];
            }
        }
        System.out.println(Math.abs(end - start));
    }
}