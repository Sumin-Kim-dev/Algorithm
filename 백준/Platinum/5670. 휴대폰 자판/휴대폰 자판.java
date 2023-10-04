import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = br.readLine();
			if (s == null || s.isEmpty()) break;
			int n = Integer.parseInt(s);
			String[] strs = new String[n];
			for (int i = 0; i < n; i++) {
				strs[i] = br.readLine();
			}
			sb.append(answer(n, strs)).append('\n');
		}
		System.out.println(sb);
	}

	private static String answer(int n, String[] strs) {
		Node head = new Node();
		for (String str : strs) {
			Node curr = head;
			for (char c : str.toCharArray()) {
				curr = curr.children.computeIfAbsent(c, key -> new Node());
				curr.nChild++;
			}
			curr.isEnd = true;
		}
		return String.format("%.2f", 1.0 * dfs(head) / n);
	}

	private static int dfs(Node node) {
		int sum = node.nChild;
		for (Node child : node.children.values()) {
			while (child.children.size() == 1 && !child.isEnd) {
				child = child.children.values().iterator().next();
			}
			sum += dfs(child);
		}
		return sum;
	}

	static class Node {
		int nChild;
		Map<Character, Node> children;
		boolean isEnd;
		
		public Node() {
			nChild = 0;
			children = new HashMap<>();
			isEnd = false;
		}
	}
}