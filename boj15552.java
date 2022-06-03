package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj15552 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(bf.readLine());

		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(bf.readLine());
			// 문자열로 바꿔주기 위해 마지막에 빈 문자열을 더함
			bw.write(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + "");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
