package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12034 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			StringBuilder sb = new StringBuilder();
			int n = Integer.parseInt(br.readLine());
			int[] price = new int[2 * n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2 * n; i++)
				price[i] = Integer.parseInt(st.nextToken());
			sb.append("Case #" + test + ": ");
			sb.append(sale(price, n));
			bw.write(sb.toString());
			bw.flush();
		}
		bw.close();
	}

	static String sale(int[] price, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int currSale = price[0];
			int currNormal = currSale / 3 * 4;
			sb.append(currSale).append(' ');
			int index = Arrays.binarySearch(price, currNormal);
			for (int j = 0; j < 2 * (n - i - 1); j++) {
				if (j < index - 1)
					price[j] = price[j + 1];
				if (j >= index - 1)
					price[j] = price[j + 2];
			}
		}
		return sb.toString();
	}
}
