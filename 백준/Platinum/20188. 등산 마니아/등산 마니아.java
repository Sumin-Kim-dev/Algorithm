import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		nodes = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node();
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].child.add(b);
			nodes[b].child.add(a);
		}
		makeTree(1);
		long answer = 0;
		for (int i = 2; i <= n; i++) {
			answer += (n - 1) * nodes[i].level;
			answer -= nodes[i].nChild * (nodes[i].nChild - 1) / 2;
		}
		System.out.println(answer);
	}

	private static void makeTree(int i) {
		Node curr = nodes[i];
		for (int child : curr.child) {
			Node next = nodes[child];
			next.child.remove(i);
			next.parent = i;
			next.level = curr.level + 1;
			makeTree(child);
			curr.nChild += next.nChild;
		}
	}

	static class Node {
		int parent;
		Set<Integer> child;
		long level;
		long nChild;
		
		public Node() {
			this.parent = 0;
			this.child = new HashSet<>();
			this.level = 0;
			this.nChild = 1;
		}
	}
}