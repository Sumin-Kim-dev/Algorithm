package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] time = time(n, k);
		bw.write(time[0] + "\n" + time[1]);
		bw.close();
	}

	static int[] time(int n, int k) {
		int[] time = new int[100001], count = new int[100001];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		time[n] = 1;
		count[n] = 1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int[] next = { curr - 1, curr + 1, 2 * curr };
			for (int i = 0; i < 3; i++) {
				if (next[i] < 0 || next[i] > 100000)
					continue;
				if (time[next[i]] == 0) {
					queue.add(next[i]);
					time[next[i]] = time[curr] + 1;
				}
				if (time[curr] < time[next[i]])
					count[next[i]] += count[curr];
			}
		}
		return new int[] { time[k] - 1, count[k] };
	}
}
