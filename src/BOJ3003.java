import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3003 {
    static final int[] chess = {1, 1, 2, 2, 2, 8};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i : chess) {
            System.out.print(i - Integer.parseInt(st.nextToken()));
            System.out.print(' ');
        }
    }
}
