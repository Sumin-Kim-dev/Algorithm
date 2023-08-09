import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				int curr = 0;
				if (!pq.isEmpty()) curr = pq.poll();
				sb.append(curr).append('\n');
			} else {
				pq.offer(x);
			}
		}
		System.out.println(sb);
	}

}