package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj13305 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		long distance[] = new long[N - 1];
		for (int i = 0; i < N - 1; i++)
			distance[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		long price[] = new long[N];
		for (int i = 0; i < N; i++)
			price[i] = Integer.parseInt(st.nextToken());

		long min = 0, currPrice = price[0];
		for (int i = 0; i < N - 1; i++) {
			if (price[i] < currPrice)
				currPrice = price[i];
			min += currPrice * distance[i];
		}
		bw.write(min + "");
		bw.close();
	}
}
