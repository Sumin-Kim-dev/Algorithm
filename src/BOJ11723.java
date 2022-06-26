

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ11723 {

	static StringBuilder sb = new StringBuilder();
	static HashSet<Integer> all = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		for (int i = 1; i <= 20; i++)
			all.add(i);

		int M = Integer.parseInt(br.readLine());
		String operator;
		int x = 0;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			operator = st.nextToken();
			if (!(operator.equals("all") || operator.equals("empty")))
				x = Integer.parseInt(st.nextToken());
			operation(operator, set, x);
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void operation(String operator, HashSet<Integer> set, int x) {
		if (operator.equals("add"))
			set.add(x);
		if (operator.equals("remove"))
			set.remove(x);
		if (operator.equals("toggle")) {
			if (set.contains(x))
				set.remove(x);
			else
				set.add(x);
		}
		if (operator.equals("all")) {
			set.clear();
			set.addAll(all);
		}
		if (operator.equals("empty"))
			set.clear();
		if (operator.equals("check")) {
			if (set.contains(x))
				sb.append("1\n");
			else
				sb.append("0\n");
		}
	}
}
