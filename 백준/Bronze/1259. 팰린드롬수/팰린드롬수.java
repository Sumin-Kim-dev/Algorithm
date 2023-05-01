import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            if(Pallindrom(n)) bw.write("yes\n");
            else bw.write("no\n");
        }
        bw.close();
    }
    static boolean Pallindrom(int n) {
        if(n < 10) return true;
        if(n < 100) {
            if(n / 10 == n % 10) return true;
            return false;
        }
        if(n < 1000) {
            if(n / 100 == n % 10) return true;
            return false;
        }
        if(n < 10000) {
            if(n / 1000 == n % 10 && (n / 100) % 10 == (n % 100) / 10) return true;
            return false;
        }
        if(n / 10000 == n % 10 && (n / 1000) % 10 == (n % 100) / 10) return true;
        return false;
    }
}
