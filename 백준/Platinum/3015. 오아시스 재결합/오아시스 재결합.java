import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<>();
		int before = Integer.parseInt(br.readLine());
		stack.push(new int[] {before, 1});

		long answer = 0;
		for (int i = 1; i < n; i++) {
			int curr = Integer.parseInt(br.readLine());
			while (!stack.isEmpty() && (before = stack.peek()[0]) < curr) {
				answer += stack.pop()[1];
			}
			if (stack.isEmpty()) {
				stack.push(new int[] {curr, 1});
				continue;
			}
			if (before == curr) {
				answer += stack.peek()[1];
				stack.peek()[1]++;
				if (stack.size() > 1) answer++;
			} else {
				answer++;
				stack.push(new int[] {curr, 1});
			}
		}
		System.out.println(answer);
	}

}
