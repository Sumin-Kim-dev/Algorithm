package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1021 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int position[] = new int[M];
		st = new StringTokenizer(br.readLine());

		RotateQueue queue = new RotateQueue(N);
		int min = 0;
		for (int i = 0; i < M; i++) {
			position[i] = Integer.parseInt(st.nextToken());
			int j = 0;
			while (position[i] != queue.front()) {
				queue.left();
				j++;
			}
			if (j > queue.size / 2)
				j = queue.size - j;
			min += j;
			queue.pop();
		}
		bw.write(min + "");
		bw.close();
	}

}

class RotateQueue {
	int queue[], size, start, end;

	RotateQueue(int N) {
		this.queue = new int[N];
		for (int i = 0; i < N; i++)
			this.queue[i] = i + 1;
		size = N;
		start = 0;
		end = 0;
	}

	public int pop() {
		int data = this.queue[start];
		start = (start + 1) % queue.length;
		size--;
		return data;
	}

	public void left() {
		this.queue[end] = this.queue[start];
		start = (start + 1) % queue.length;
		end = (end + 1) % queue.length;
	}

	public int front() {
		return this.queue[start];
	}
}