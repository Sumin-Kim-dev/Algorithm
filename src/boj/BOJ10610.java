package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ10610 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String n = br.readLine();
		int[] nInt = new int[n.length()];
		int three = 0, ten = 0;
		for (int i = 0; i < n.length(); i++) {
			nInt[i] = n.charAt(i) - '0';
			three += nInt[i];
			if (nInt[i] == 0)
				ten = 1;
		}
		if (three % 3 != 0 || ten == 0)
			sb.append(-1);
		else {
			Arrays.sort(nInt);
			for (int i = n.length() - 1; i >= 0; i--)
				sb.append(nInt[i]);
		}
		bw.write(sb.toString());
		bw.close();
	}

}
