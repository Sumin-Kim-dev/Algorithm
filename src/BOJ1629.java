import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1629 {

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
