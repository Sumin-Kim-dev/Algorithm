import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			List<Integer>[] small = new List[n];
			List<Integer>[] big = new List[n];
			for (int i = 0; i < n; i++) {
				small[i] = new ArrayList<>();
				big[i] = new ArrayList<>();
			}
			while (m-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				small[b].add(a);
				big[a].add(b);
			}
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (dfs(i, small, n) + dfs(i, big, n) == n - 1) count++;
			}
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	private static int dfs(int i, List<Integer>[] adj, int n) {
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack<>();
		stack.push(i);
		visited[i] = true;
		int count = 0;
		while (!stack.isEmpty()) {
			int curr = stack.pop();
			for (int next : adj[curr]) {
				if (visited[next]) continue;
				visited[next] = true;
				stack.push(next);
				count++;
			}
		}
		return count;
	}

}