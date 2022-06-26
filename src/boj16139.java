

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj16139 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String s = br.readLine();
		int count[][] = new int[26][s.length() + 1];
		for (int j = 1; j <= s.length(); j++) {
			for (int i = 0; i < 26; i++) {
				count[i][j] = count[i][j - 1];
				if (s.charAt(j - 1) - 'a' == i)
					count[i][j]++;
			}
		}
		int q = Integer.parseInt(br.readLine());
		int start, end;
		char alphabet;
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			alphabet = st.nextToken().charAt(0);
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			sb.append(count[alphabet - 'a'][end + 1] - count[alphabet - 'a'][start]).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

}
