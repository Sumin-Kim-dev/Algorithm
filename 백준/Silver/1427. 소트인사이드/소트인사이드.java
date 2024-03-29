import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        while(N != 0) {
            a.add(N % 10);
            N /= 10;
        }
        Collections.sort(a, Collections.reverseOrder());
        for(int i = 0; i < a.size(); i++) {
            bw.write(a.get(i)+"");
        }
        bw.close();
    }
}