package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ5639 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		BST bst = new BST();
		String str = "";
		while (true) {
			str = br.readLine();
			if (str == null || str.isBlank())
				break;
			int node = Integer.parseInt(str);
			bst.add(node);
		}
		bw.write(postorder(bst));
		bw.close();
	}

	static String postorder(BST bst) {
		Node head = bst.head;
		return postorder(bst, head);
	}

	static String postorder(BST bst, Node node) {
		if (node == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(postorder(bst, node.leftChild));
		sb.append(postorder(bst, node.rightChild));
		sb.append(node.data).append('\n');
		return sb.toString();
	}
}

class BST {
	Node head;

	BST() {
		head = null;
	}

	void add(int data) {
		head = add(data, head);
	}

	Node add(int data, Node curr) {
		if (curr == null)
			curr = new Node(data);
		if (data < curr.data)
			curr.leftChild = add(data, curr.leftChild);
		if (data > curr.data)
			curr.rightChild = add(data, curr.rightChild);
		return curr;
	}
}

class Node {
	int data;
	Node leftChild, rightChild;

	Node(int data) {
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
}