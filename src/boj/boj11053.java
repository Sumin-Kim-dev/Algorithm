package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11053 {

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
		int seq[] = new int[n];
		seq[0] = 1;
		int temp, max = 1;
		for (int i = 1; i < n; i++) {
			temp = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && seq[j] + 1 > temp)
					temp = seq[j] + 1;
			}
			seq[i] = temp;
			if (seq[i] > max)
				max = seq[i];
		}
		bw.write(max + "");
		bw.close();
	}

}
