package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj1269 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		HashSet<Integer> A = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++) {
			int element = Integer.parseInt(st.nextToken());
			if (A.contains(element))
				A.remove(element);
		}

		// A-B 원소 개수 = A.size(), B-A 원소 개수 = b - (a- A.size())
		bw.write(b - a + 2 * A.size() + "");
		bw.close();
	}

}
