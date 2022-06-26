import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16953 {

	static int a, b;
	static Queue<long[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		bw.write(bfs() + "");
		bw.close();
	}

	static long bfs() {
		long start[] = { a, 1 };
		queue.add(start);
		while (!queue.isEmpty()) {
			long num[] = queue.poll();
			if (num[0] == b)
				return num[1];
			if (num[0] * 2 <= b) {
				long newNum[] = { num[0] * 2, num[1] + 1 };
				queue.add(newNum);
			}
			if (num[0] * 10 + 1 <= b) {
				long newNum[] = { num[0] * 10 + 1, num[1] + 1 };
				queue.add(newNum);
			}
		}
		return -1;
	}
}