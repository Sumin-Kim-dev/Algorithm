package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2407 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		bw.write(combination(n, m));
		bw.close();
	}

	static String combination(int n, int m) {
		String comb[][] = new String[n + 1][m + 1];
		comb[0][0] = "1";
		for (int j = 1; j <= m; j++)
			comb[0][j] = "0";
		for (int i = 1; i <= n; i++) {
			comb[i][0] = "1";
			for (int j = 1; j <= m; j++) {
				comb[i][j] = add(comb[i - 1][j - 1], comb[i - 1][j]);
			}
		}
		return comb[n][m];
	}

	static String add(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		int len = len1;
		if (len2 > len1)
			len = len2;

		int num1[] = new int[len];
		int num2[] = new int[len];
		int numAdd[] = new int[len + 1];
		int carry = 0;
		for (int i = 0; i < len; i++) {
			if (i < len1)
				num1[i] = s1.charAt(len1 - i - 1) - '0';
			if (i < len2)
				num2[i] = s2.charAt(len2 - i - 1) - '0';
		}
		for (int i = 0; i < len; i++) {
			numAdd[i] = num1[i] + num2[i] + carry;
			carry = numAdd[i] / 10;
			numAdd[i] %= 10;
		}
		numAdd[len] = carry;

		String add = "";
		for (int i = len; i >= 0; i--) {
			if (i == len && numAdd[i] == 0)
				continue;
			add += numAdd[i];
		}
		return add;
	}
}
