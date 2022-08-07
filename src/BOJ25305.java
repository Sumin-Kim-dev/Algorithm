import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ25305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Integer[] scores = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            scores[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(scores, (o1, o2) -> o2 - o1);
        System.out.println(scores[k - 1]);
    }
}
