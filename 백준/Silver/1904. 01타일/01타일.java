import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int a0 = 1, a1 = 0, a2 = 1;
        int temp;
        for(int i = 0; i < N; i++) {
            temp = a2;
            a2 = (a1 + a2) % 15746;
            a0 = a1;
            a1 = temp;
        }
        bw.write("" + a2);
        bw.close();
    }
}