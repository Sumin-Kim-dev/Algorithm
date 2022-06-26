

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10942 {

	static int n, num[], palindrome[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		num = new int[n];
		palindrome = new int[n][n];
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		int s, e;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			sb.append(palindrome(s, e) - 1).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	// 체크 안했으면 0 팰린드롬 아니면 1 팰린드롬이면 2
	static int palindrome(int s, int e) {
		if (palindrome[s - 1][e - 1] != 0)
			return palindrome[s - 1][e - 1];
		if (num[s - 1] != num[e - 1])
			return 1;
		if (e - s < 2)
			return 2;
		palindrome[s - 1][e - 1] = palindrome(s + 1, e - 1);
		return palindrome[s - 1][e - 1];
	}
}
