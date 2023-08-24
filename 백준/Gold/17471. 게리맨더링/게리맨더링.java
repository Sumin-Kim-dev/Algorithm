import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] people;
	static int[][] adj;
	
	static int sum;
	static int min;
	static boolean[] group;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		people = new int[n];
		group = new boolean[n];
		sum = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			sum += people[i];
		}
		adj = new int[n][];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			adj[i] = new int[m];
			for (int j = 0; j < m; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		subSet(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void subSet(int cnt, int curr) {
		if (cnt == n - 1) {
			if (isAble()) {
				min = Math.min(min, Math.abs(sum - 2 * curr));
			}
			return;
		}
		group[cnt] = true;
		subSet(cnt + 1, curr + people[cnt]);
		group[cnt] = false;
		subSet(cnt + 1, curr);
	}

	private static boolean isAble() {
		int start = n - 1;
		while (start > 0 && !group[--start]);
		if (start == -1) return false;
		return (bfs(start, true) | bfs(n - 1, false)) == (1 << n) - 1;
	}

	private static int bfs(int start, boolean b) {
		int flag = 1 << start;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int next : adj[curr]) {
				if (group[next] ^ b) continue;
				if ((flag & 1 << next) != 0) continue;
				flag |= 1 << next;
				queue.offer(next);
			}
		}
		return flag;
	}

}