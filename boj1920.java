package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a[] = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int b[] = new int[M];
		for (int i = 0; i < M; i++) {
			b[i] = Integer.parseInt(st.nextToken());
			if (isIn(b[i], a, 0, N))
				bw.write("1\n");
			else
				bw.write("0\n");
		}
		bw.close();

	}

	static boolean isIn(int b, int a[], int min, int count) {
		if (count == 0)
			return false;
		if (count == 1)
			return b == a[min];
		return b > a[min + (count - 1) / 2] ? isIn(b, a, min + (count + 1) / 2, count / 2)
				: isIn(b, a, min, (count + 1) / 2);
	}
}