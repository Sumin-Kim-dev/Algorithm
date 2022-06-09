package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj1764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> hear = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hear.add(br.readLine());
		}

		int count = 0;
		ArrayList<String> hearSee = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String see = br.readLine();
			if (hear.contains(see)) {
				hearSee.add(see);
				count++;
			}
		}
		Collections.sort(hearSee);
		sb.append(count);
		sb.append("\n");

		for (int i = 0; i < hearSee.size(); i++) {
			sb.append(hearSee.get(i));
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

}
