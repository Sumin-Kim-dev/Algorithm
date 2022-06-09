package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj14425 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> stringSet = new HashSet<>();
		for (int i = 0; i < N; i++) {
			stringSet.add(br.readLine());
		}

		int count = 0;
		for (int i = 0; i < M; i++) {
			if (stringSet.contains(br.readLine()))
				count++;
		}
		bw.write(count + "");
		bw.close();

	}

}
