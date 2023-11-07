import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer>[] pqs = new PriorityQueue[1001];
		for (int i = 1; i <= 1000; i++) {
			pqs[i] = new PriorityQueue<>(Comparator.reverseOrder());
		}
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pqs[d].offer(w);
		}
		int maxScore = 0;
		for (int curr = 1000; curr > 0; curr--) {
			int currMax = 0;
			int currMaxD = 0;
			for (int d = 1000; d >= curr; d--) {
				if (pqs[d].isEmpty()) continue;
				int currW = pqs[d].peek();
				if (currW > currMax) {
					currMax = currW;
					currMaxD = d;
				}
			}
			if (currMaxD == 0) continue;
			maxScore += pqs[currMaxD].poll();
		}
		System.out.println(maxScore);
	}

}