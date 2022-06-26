import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ13549 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		bw.write(time(n, k) + "");
		bw.close();
	}

	static int time(int n, int k) {
		int[] time = new int[100001];
		Deque<Integer> deque = new LinkedList<>();
		deque.addLast(n);
		time[n] = 1;
		while (!deque.isEmpty()) {
			int curr = deque.pollFirst();
			if (curr * 2 <= 100000 && (time[curr * 2] == 0 || time[curr * 2] > time[curr])) {
				deque.addFirst(curr * 2);
				time[curr * 2] = time[curr];
			}
			int[] next = new int[] { curr - 1, curr + 1 };
			for (int i = 0; i < 2; i++) {
				if (next[i] < 0 || next[i] > 100000)
					continue;
				if (next[i] >= 0 && (time[next[i]] == 0 || time[next[i]] > time[curr] + 1)) {
					deque.addLast(next[i]);
					time[next[i]] = time[curr] + 1;
				}
			}
			if (time[k] != 0)
				return time[k] - 1;
		}
		return -1;
	}
}
