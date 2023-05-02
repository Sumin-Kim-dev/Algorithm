import java.io.*;
import java.util.*;

public class Main {

	static int n, k, time[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		time = new int[100001];
		for (int i = 0; i < 100001; i++)
			time[i] = -1;
		bw.write(time() + "");
		bw.close();
	}

	static int time() {
		Queue<Integer> queue = new LinkedList<>();
		time[n] = 0;
		queue.add(n);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current > 0 && time[current - 1] == -1) {
				queue.add(current - 1);
				time[current - 1] = time[current] + 1;
			}
			if (current < 100000 && time[current + 1] == -1) {
				queue.add(current + 1);
				time[current + 1] = time[current] + 1;
			}
			if (current * 2 <= 100000 && time[current * 2] == -1) {
				queue.add(current * 2);
				time[current * 2] = time[current] + 1;
			}
		}
		return time[k];
	}
}
