import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int stack[] = new int[N];
		for (int i = 0; i < N; i++) {
			String command = br.readLine();
			if (command.contains("push")) {
				int data = Integer.parseInt(command.split(" ")[1]);
				push(stack, data);
			}
			if (command.equals("pop"))
				System.out.println(pop(stack));
			if (command.equals("size"))
				System.out.println(size(stack));
			if (command.equals("empty"))
				System.out.println(empty(stack));
			if (command.equals("top"))
				System.out.println(top(stack));
		}
	}

	static void push(int stack[], int data) {
		stack[size(stack)] = data;
	}

	static int pop(int stack[]) {
		if (size(stack) == 0)
			return -1;
		int data = stack[size(stack) - 1];
		stack[size(stack) - 1] = 0;
		return data;
	}

	static int size(int stack[]) {
		int i = 0;
		while (stack[i] != 0)
			i++;
		return i;
	}

	static int empty(int stack[]) {
		if (size(stack) == 0)
			return 1;
		return 0;
	}

	static int top(int stack[]) {
		if (size(stack) == 0)
			return -1;
		return stack[size(stack) - 1];
	}
}