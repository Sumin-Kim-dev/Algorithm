package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj10986 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int a[] = new int[N];
		int residue = 0;
		long rFreq[] = new long[M];
		rFreq[0] = 1;
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			residue = (residue + a[i]) % M;
			rFreq[residue]++;
		}

		long count = 0;
		for (int i = 0; i < M; i++) {
			count += rFreq[i] * (rFreq[i] - 1) / 2;
		}
		bw.write(count + "");
		bw.close();
	}

}
