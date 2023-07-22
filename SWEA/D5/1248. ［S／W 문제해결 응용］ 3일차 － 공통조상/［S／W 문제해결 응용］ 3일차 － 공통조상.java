import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static int v;
	static int[][] sTable;
	static int[][] children;
	static int[] depth;
	static int[] subTreeSize;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			sTable = new int[v][32 - Integer.numberOfLeadingZeros(v)];
			children = new int[2][v];
			for (int i = 0; i < 2; i++) {
				Arrays.fill(children[i], -1);
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < e; i++) {
				int parent = Integer.parseInt(st.nextToken()) - 1;
				int child = Integer.parseInt(st.nextToken()) - 1;
				sTable[child][0] = parent;
				if (children[0][parent] == -1) {
					children[0][parent] = child;
				} else {
					children[1][parent] = child;
				}
			}
			depth = new int[v];
			subTreeSize = new int[v];
			setDepth();
			setSubTreeSize();
			int lca = findLCA(v1, v2);
			sb.append(lca + 1).append(" ").append(subTreeSize[lca]).append("\n");
		}
		System.out.println(sb);
	}

	private static int findLCA(int v1, int v2) {
		for (int i = 1; i < sTable[0].length; i++) {
			for (int j = 0; j < v; j++) {
				sTable[j][i] = sTable[sTable[j][i - 1]][i - 1];
			}
		}
		int depth1 = depth[v1];
		int depth2 = depth[v2];
		if (depth1 > depth2) {
			int temp = v1;
			v1 = v2;
			v2 = temp;
		}
		int diff = depth[v2] - depth[v1];
		int d = 0;
		while (diff > 0) {
			if (diff % 2 == 1) {
				v2 = sTable[v2][d];
			}
			d++;
			diff /= 2;
		}
		int logv = 32 - Integer.numberOfLeadingZeros(v);
		int common;
		while (true) {
			common = logv - 1;
			while (common >= 0 && sTable[v1][common] == sTable[v2][common]) {
				common--;
			}
			if (common == -1) return sTable[v1][0];
			v1 = sTable[v1][common];
			v2 = sTable[v2][common];
		}
	}

	private static void setSubTreeSize() {
		setSubTreeSize(0);
	}

	private static int setSubTreeSize(int curr) {
		subTreeSize[curr] = 1;
		for (int i = 0; i < 2; i++) {
			int next = children[i][curr];
			if (next == -1) continue;
			subTreeSize[curr] += setSubTreeSize(next);
		}
		return subTreeSize[curr];
	}

	private static void setDepth() {
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		while (!stack.isEmpty()) {
			int curr = stack.pop();
			for (int i = 0; i < 2; i++) {
				int next = children[i][curr];
				if (next == -1) continue;
				depth[next] = depth[curr] + 1;
				stack.push(next);
			}
		}
	}
}
