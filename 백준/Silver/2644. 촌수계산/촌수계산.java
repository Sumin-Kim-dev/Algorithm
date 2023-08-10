import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()) - 1;
		int v2 = Integer.parseInt(st.nextToken()) - 1;
		List<Integer>[] lists = new List[n];
		for (int i = 0; i < n; i++) {
			lists[i] = new ArrayList<>();
		}
		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			lists[x].add(y);
			lists[y].add(x);
		}
		int[] result = new int[n];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(v1);
		result[v1] = 1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int next : lists[curr]) {
				if (result[next] > 0) continue;
				if (next == v2) {
					System.out.println(result[curr]);
					return;
				}
				result[next] = result[curr] + 1;
				queue.offer(next);
			}
		}
		System.out.println(-1);
	}

}