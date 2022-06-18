package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11054 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int incSeq[] = incSeq(a);
		int decSeq[] = decSeq(a);
		int max = 1;
		for (int i = 0; i < n; i++) {
			if (incSeq[i] + decSeq[i] - 1 > max)
				max = incSeq[i] + decSeq[i] - 1;
		}
		bw.write(max + "");
		bw.close();
	}

	static int[] incSeq(int a[]) {
		int seq[] = new int[a.length];
		seq[0] = 1;
		int temp = 1;
		for (int i = 1; i < a.length; i++) {
			temp = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && seq[j] + 1 > temp)
					temp = seq[j] + 1;
			}
			seq[i] = temp;
		}
		return seq;
	}

	static int[] decSeq(int a[]) {
		int b[] = new int[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[a.length - 1 - i];
		int seq[] = incSeq(b);
		int decSeq[] = new int[a.length];
		for (int i = 0; i < a.length; i++)
			decSeq[i] = seq[a.length - 1 - i];
		return decSeq;
	}
}
