

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj9375 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int n;
		String cloth;
		int cases = 1;
		Object[] number;
		HashMap<String, Integer> clothes = new HashMap<>();
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				cloth = st.nextToken();
				clothes.put(cloth, clothes.getOrDefault(cloth, 0) + 1);
			}
			number = clothes.values().toArray();
			for (int j = 0; j < number.length; j++) {
				cases *= (int) (number[j]) + 1;
			}
			cases -= 1;
			sb.append(cases + "\n");
			clothes.clear();
			cases = 1;
		}
		bw.write(sb.toString());
		bw.close();
	}

}
