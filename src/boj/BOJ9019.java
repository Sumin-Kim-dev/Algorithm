package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(operator(a, b)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static String operator(int a, int b) {
		String command[] = new String[10000];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(a);
		command[a] = "";
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (command[D(curr)] == null) {
				command[D(curr)] = command[curr] + "D";
				queue.add(D(curr));
			}
			if (command[S(curr)] == null) {
				command[S(curr)] = command[curr] + "S";
				queue.add(S(curr));
			}
			if (command[L(curr)] == null) {
				command[L(curr)] = command[curr] + "L";
				queue.add(L(curr));
			}
			if (command[R(curr)] == null) {
				command[R(curr)] = command[curr] + "R";
				queue.add(R(curr));
			}
			if (command[b] != null)
				return command[b];
		}
		return command[b];
	}

	static int D(int a) {
		return (2 * a) % 10000;
	}

	static int S(int a) {
		return (a + 9999) % 10000;
	}

	static int L(int a) {
		return (a % 1000) * 10 + a / 1000;
	}

	static int R(int a) {
		return (a % 10) * 1000 + a / 10;
	}
}
