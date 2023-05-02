import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int x, median = 0;
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> large = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			x = Integer.parseInt(br.readLine());
			if (i == 0)
				median = x;
			else if (i % 2 == 1) {
				if (x < median) {
					small.add(x);
					large.add(median);
					median = small.poll();
				} else
					large.add(x);
			} else {
				if (x < median)
					small.add(x);
				else {
					large.add(x);
					small.add(median);
					median = large.poll();
				}
			}
			sb.append(median).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}
}