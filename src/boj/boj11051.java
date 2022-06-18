package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11051 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		bw.write(Combination(N, K) + "");
		bw.close();
	}

	static int Combination(int N, int K) {
		int combination[] = new int[K + 1];
		int temp[] = new int[K + 1];

		// 0C0 = 1, 0C1, ..., 0Ck = 0
		combination[0] = 1;

		// nCk = (n-1)C(k-1)+(n-1)Ck
		int i = 1;
		while (i <= N) {
			temp = combination.clone();
			for (int j = 1; j < K + 1; j++)
				combination[j] = (temp[j - 1] + temp[j]) % 10007;
			i++;
		}
		return combination[K];
	}
}
