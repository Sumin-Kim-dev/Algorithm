

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class boj7662 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());
		int k;
		String command;
		DoublePriorityQueue queue;
		for (int i = 0; i < t; i++) {
			k = Integer.parseInt(br.readLine());
			queue = new DoublePriorityQueue();
			for (int j = 0; j < k; j++) {
				command = br.readLine();
				queue.command(command);
			}
			queue.print();
			bw.write(queue.sb.toString());
		}
		bw.close();
	}

}

class DoublePriorityQueue {
	int capacity, size;
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;
	HashMap<Integer, Integer> map;
	StringBuilder sb;

	DoublePriorityQueue() {
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		this.map = new HashMap<>();
		this.size = 0;
		this.sb = new StringBuilder();
	}

	public void command(String command) {
		String commandSplit[] = command.split(" ");
		char op = commandSplit[0].charAt(0);
		int x = Integer.parseInt(commandSplit[1]);
		if (op == 'I')
			this.insert(x);
		if (op == 'D')
			this.delete(x);
	}

	private void insert(int x) {
		size++;
		minHeap.add(x);
		maxHeap.add(x);
		map.put(x, map.getOrDefault(x, 0) + 1);
	}

	private int delete(int x) {
		if (size == 0 || x != -1 && x != 1) // ����ִ� ���
			return 0;
		size--;
		if (x == -1) { // �ּҰ� ����
			int min = minHeap.poll();
			while (map.get(min) == 0) {
				min = minHeap.poll();
			}
			map.replace(min, map.get(min) - 1);
			return min;
		} else { // �ִ밪 ����
			int max = maxHeap.poll();
			while (map.get(max) == 0) {
				max = maxHeap.poll();
			}
			map.replace(max, map.get(max) - 1);
			return max;
		}
	}

	public void print() {
		if (this.size == 0)
			sb.append("EMPTY\n");
		else {
			int max = this.delete(1);
			int min;
			if (this.size == 0)
				min = max;
			else
				min = this.delete(-1);
			sb.append(max).append(' ').append(min).append('\n');
		}
	}
}
