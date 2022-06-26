import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {

	static HashMap<Integer, Integer> map = new HashMap<>();
	static int[] dist = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n + m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			map.put(v1, v2);
		}
		bw.write(min() + "");
		bw.close();
	}

	static int min() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		dist[1] = 1;
		while (!queue.isEmpty()) {
			int start = queue.poll();
			for (int i = 1; i <= 6; i++) {
				int end = start + i;
				if (end <= 100 && dist[end] == 0) {
					if (map.get(end) != null) {
						dist[end] = dist[start] + 1;
						end = map.get(end);
					}
					queue.add(end);
					if (dist[end] == 0 || dist[start] < dist[end])
						dist[end] = dist[start] + 1;
					if (dist[100] != 0)
						return dist[100] - 1;
				}
			}
		}
		return -1;
	}
}
