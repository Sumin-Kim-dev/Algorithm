import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10845 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int queue[] = new int[N];
		for (int i = 0; i < N; i++) {
			String command = br.readLine();
			if (command.contains("push")) {
				int data = Integer.parseInt(command.split(" ")[1]);
				push(queue, data);
			}
			if (command.equals("pop"))
				System.out.println(pop(queue));
			if (command.equals("size"))
				System.out.println(size(queue));
			if (command.equals("empty"))
				System.out.println(empty(queue));
			if (command.equals("front"))
				System.out.println(front(queue));
			if (command.equals("back"))
				System.out.println(back(queue));
		}
	}

	static void push(int queue[], int data) {
		int size = size(queue);
		for (int i = size - 1; i >= 0; i--) {
			queue[i + 1] = queue[i];
		}
		queue[0] = data;
	}

	static int pop(int queue[]) {
		if (size(queue) == 0)
			return -1;
		int data = queue[size(queue) - 1];
		queue[size(queue) - 1] = 0;
		return data;
	}

	static int size(int queue[]) {
		int i = 0;
		while (queue[i] != 0)
			i++;
		return i;
	}

	static int empty(int queue[]) {
		if (size(queue) == 0)
			return 1;
		return 0;
	}

	static int front(int queue[]) {
		if (size(queue) == 0)
			return -1;
		return queue[size(queue) - 1];
	}

	static int back(int queue[]) {
		if (size(queue) == 0)
			return -1;
		return queue[0];
	}
}