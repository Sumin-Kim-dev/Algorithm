import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		long ans = 1;
		String binaryB = Integer.toBinaryString(b);
		for (int i = 0; i < binaryB.length(); i++) {
			ans = (ans * ans) % c;
			if (binaryB.charAt(i) == '1')
				ans = (ans * a) % c;
		}
		bw.write(ans + "");
		bw.close();
	}

}
