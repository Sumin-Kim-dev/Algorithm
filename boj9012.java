package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj9012 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String ps = br.readLine();
			if (isVPS(ps))
				bw.write("YES\n");
			else
				bw.write("NO\n");
		}
		bw.close();
	}

	static boolean isVPS(String ps) {
		int left = 0, right = 0;
		for (int i = 0; i < ps.length(); i++) {
			if (ps.charAt(i) == '(')
				left++;
			if (ps.charAt(i) == ')')
				right++;
			if (right > left)
				return false;
		}
		if (right != left)
			return false;
		return true;
	}
}
