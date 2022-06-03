package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2675 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(bf.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			for (int j = 0; j < s.length(); j++) {
				for (int k = 0; k < R; k++) {
					bw.write(s.charAt(j));
				}
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}