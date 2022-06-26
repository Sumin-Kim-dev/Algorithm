import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);

		String scale = "";
		for (int i = 0; i < 8; i++) {
			scale += st.nextToken();
		}
		if (scale.equals("12345678"))
			bw.write("ascending");
		else if (scale.equals("87654321"))
			bw.write("descending");
		else
			bw.write("mixed");

		bw.flush();
		bw.close();
	}
}