package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int x;
		MinHeap heap = new MinHeap(N);
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

class MinHeap {
	int heap[], size;
	StringBuilder sb;

	MinHeap(int N) {
		this.heap = new int[N];
		this.size = 0;
		this.sb = new StringBuilder();
	}

	public void add(int x) {
		size++;
		heap[size - 1] = x;
		int i = size - 1; // x가 있는 노드
		int j; // i의 부모 노드
		int temp;
		while (i > 0) {
			j = (i - 1) / 2;
			if (x < heap[j]) { // 대소 관계 반대면 교환
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
		int min = heap[0];
		heap[0] = heap[size - 1];
		size--;
		int i = 0; // 이동해야하는 값이 있는 노드
		int j; // i의 자식 노드 중 작은 값이 있는 노드
		int temp;
		while (i <= (size - 1) / 2) {
			j = 2 * i + 1;
			if (j + 1 < size && heap[j + 1] < heap[j])
				j++;
			if (heap[j] < heap[i]) { // 대소 관계 반대면 교환
				temp = heap[j];
				heap[j] = heap[i];
				heap[i] = temp;
				i = j;
			} else
				break;
		}
		sb.append(min).append('\n');
	}
}