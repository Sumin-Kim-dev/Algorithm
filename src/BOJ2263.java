

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2263 {

	static int[] inorder, postorder, inorderIndex;
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		inorder = new int[n];
		postorder = new int[n];
		inorderIndex = new int[n + 1];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st1.nextToken());
			postorder[i] = Integer.parseInt(st2.nextToken());
			inorderIndex[inorder[i]] = i;
		}
		preorder(0, n, 0, n);
		bw.write(sb.toString());
		bw.close();
	}

	static void preorder(int inStart, int inEnd, int postStart, int postEnd) {
		int n = postEnd - postStart;
		// 0���� null tree -> ��� ����
		if (n == 0)
			return;

		// ���Ұ� ������ head ���Ҵ� postorder�� �� ��
		int head = postorder[postEnd - 1];
		int headIndex = inorderIndex[head];
		int leftLength = headIndex - inStart;

		// ���
		sb.append(head).append(' ');
		preorder(inStart, headIndex, postStart, postStart + leftLength);
		preorder(headIndex + 1, inEnd, postStart + leftLength, postEnd - 1);
		return;
	}
}