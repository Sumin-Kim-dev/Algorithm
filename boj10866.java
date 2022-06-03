package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int deque[] = new int[N];
		for (int i = 0; i < N; i++) {
			String command = br.readLine();
			if (command.contains("push_front")) {
				int data = Integer.parseInt(command.split(" ")[1]);
				push_front(deque, data);
			}
			if (command.contains("push_back")) {
				int data = Integer.parseInt(command.split(" ")[1]);
				push_back(deque, data);
			}
			if (command.equals("pop_front"))
				System.out.println(pop_front(deque));
			if (command.equals("pop_back"))
				System.out.println(pop_back(deque));
			if (command.equals("size"))
				System.out.println(size(deque));
			if (command.equals("empty"))
				System.out.println(empty(deque));
			if (command.equals("front"))
				System.out.println(front(deque));
			if (command.equals("back"))
				System.out.println(back(deque));
		}
	}

	static void push_front(int deque[], int data) {
		deque[size(deque)] = data;
	}

	static void push_back(int deque[], int data) {
		int size = size(deque);
		for (int i = size - 1; i >= 0; i--) {
			deque[i + 1] = deque[i];
		}
		deque[0] = data;
	}

	static int pop_front(int deque[]) {
		if (size(deque) == 0)
			return -1;
		int data = deque[size(deque) - 1];
		deque[size(deque) - 1] = 0;
		return data;
	}

	static int pop_back(int deque[]) {
		if (size(deque) == 0)
			return -1;
		int data = deque[0];
		int size = size(deque);
		for (int i = 1; i < size; i++) {
			deque[i - 1] = deque[i];
		}
		deque[size - 1] = 0;
		return data;
	}

	static int size(int deque[]) {
		int i = 0;
		while (deque[i] != 0)
			i++;
		return i;
	}

	static int empty(int deque[]) {
		if (size(deque) == 0)
			return 1;
		return 0;
	}

	static int front(int deque[]) {
		if (size(deque) == 0)
			return -1;
		return deque[size(deque) - 1];
	}

	static int back(int deque[]) {
		if (size(deque) == 0)
			return -1;
		return deque[0];
	}
}