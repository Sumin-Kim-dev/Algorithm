package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int x;
		MaxHeap heap = new MaxHeap(N);
		for (int i = 0; i < N; i++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0)
				heap.pop();
			else
				heap.add(x);
		}
		bw.write(heap.sb.toString());
		bw.close();
	}

}

class MaxHeap {
	int heap[], size;
	StringBuilder sb;

	MaxHeap(int N) {
		this.heap = new int[N];
		this.size = 0;
		this.sb = new StringBuilder();
	}

	public void add(int x) {
		size++;
		heap[size - 1] = x;
		int i = size - 1; // x�� �ִ� ���
		int j; // i�� �θ� ���
		int temp;
		while (i > 0) {
			j = (i - 1) / 2;
			if (x > heap[j]) { // ��� ���� �ݴ�� ��ȯ
				temp = heap[j];
				heap[j] = x;
				heap[i] = temp;
				i = j;
			} else
				break;
		}
	}

	public void pop() {
		if (size == 0) {
			sb.append("0\n");
			return;
		}
		int max = heap[0];
		heap[0] = heap[size - 1];
		size--;
		int i = 0; // �̵��ؾ��ϴ� ���� �ִ� ���
		int j; // i�� �ڽ� ��� �� ū ���� �ִ� ���
		int temp;
		while (i <= (size - 1) / 2) {
			j = 2 * i + 1;
			if (j + 1 < size && heap[j + 1] > heap[j])
				j++;
			if (heap[j] > heap[i]) { // ��� ���� �ݴ�� ��ȯ
				temp = heap[j];
				heap[j] = heap[i];
				heap[i] = temp;
				i = j;
			} else
				break;
		}
		sb.append(max).append('\n');
	}
}