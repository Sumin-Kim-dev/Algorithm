import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        int factors[] = new int[N];
        for(int i = 0; i < N; i++) {
            factors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(factors);
        bw.write(factors[0] * factors[N-1] + "");
        bw.close();
    }
}