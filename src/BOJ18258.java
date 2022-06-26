import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ18258 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Queue queue = new Queue(N);

		String func;
		int data;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			func = st.nextToken();
			if (func.equals("push")) {
				data = Integer.parseInt(st.nextToken());
				queue.push(data);
			}
			if (func.equals("pop"))
				queue.pop();
			if (func.equals("size"))
				queue.size();
			if (func.equals("empty"))
				queue.empty();
			if (func.equals("front"))
				queue.front();
			if (func.equals("back"))
				queue.back();
		}
		bw.write(queue.sb.toString());
		bw.close();
	}

}

class Queue {
	int queue[], size, start, end;
	StringBuilder sb;

	Queue(int N) {
		this.queue = new int[N];
		this.size = 0;
		this.start = 0;
		this.end = -1;
		sb = new StringBuilder();
	}

	public void push(int data) {
		end = (end + 1) % queue.length;
		this.queue[end % queue.length] = data;
		size++;
	}

	public void pop() {
		if (size > 0) {
			sb.append(this.queue[start]).append('\n');
			start = (start + 1) % queue.length;
			size--;
		} else
			sb.append("-1\n");
	}

	public void size() {
		sb.append(size).append('\n');
	}

	public void empty() {
		if (size == 0)
			sb.append(1);
		else
			sb.append(0);
		sb.append('\n');
	}

	public void front() {
		if (size == 0)
			sb.append(-1);
		else
			sb.append(this.queue[start]);
		sb.append('\n');
	}

	public void back() {
		if (size == 0)
			sb.append(-1);
		else
			sb.append(this.queue[end]);
		sb.append('\n');
	}
}