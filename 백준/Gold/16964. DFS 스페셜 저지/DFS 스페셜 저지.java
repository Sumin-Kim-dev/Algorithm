import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<Integer>[] adj = new Set[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new HashSet<>();
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			adj[s].add(e);
			adj[e].add(s);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int before = Integer.parseInt(st.nextToken()) - 1;
		if (before != 0) {
			System.out.println(0);
			return;
		}
		int[] parent = new int[n];
		parent[before] = -1;
		for (int i = 1; i < n; i++) {
			int curr = Integer.parseInt(st.nextToken()) - 1;
			while (adj[before].isEmpty()) {
				before = parent[before];
			}
			if (!adj[before].contains(curr)) {
				System.out.println(0);
				return;
			}
			parent[curr] = before;
			adj[before].remove(curr);
			adj[curr].remove(before);
			before = curr;
		}
		System.out.println(1);
	}

}