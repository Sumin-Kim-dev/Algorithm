package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1991 {

	static int[][] binaryTree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		binaryTree = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0) - 'A';
			binaryTree[node][0] = st.nextToken().charAt(0) - 'A';
			binaryTree[node][1] = st.nextToken().charAt(0) - 'A';
		}
		preorder(0);
		sb.append('\n');
		inorder(0);
		sb.append('\n');
		postorder(0);
		bw.write(sb.toString());
		bw.close();
	}

	static void preorder(int start) {
		sb.append((char) (start + 'A'));
		if (binaryTree[start][0] != '.' - 'A')
			preorder(binaryTree[start][0]);
		if (binaryTree[start][1] != '.' - 'A')
			preorder(binaryTree[start][1]);
	}

	static void inorder(int start) {
		if (binaryTree[start][0] != '.' - 'A')
			inorder(binaryTree[start][0]);
		sb.append((char) (start + 'A'));
		if (binaryTree[start][1] != '.' - 'A')
			inorder(binaryTree[start][1]);
	}

	static void postorder(int start) {
		if (binaryTree[start][0] != '.' - 'A')
			postorder(binaryTree[start][0]);
		if (binaryTree[start][1] != '.' - 'A')
			postorder(binaryTree[start][1]);
		sb.append((char) (start + 'A'));
	}
}
